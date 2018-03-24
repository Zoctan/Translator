package api;

import api.annotation.ApiComponent;
import api.impl.BaiduApi;
import api.impl.GoogleApi;
import api.impl.YoudaoApi;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class ApiFactory {

    private final Map<String, AbstractApi> ApiMap = new HashMap<>();
    private final List<String> workPackages = new ArrayList<>();

    private final List<Class<? extends AbstractApi>> ApiClasses = Arrays.asList(
            YoudaoApi.class,
            BaiduApi.class,
            GoogleApi.class);

    public AbstractApi get(final String name) {
        return ApiMap.get(name);
    }

    public ApiFactory() throws Exception {
        this.initSystemApi();
        this.initUserApi();
    }

    public ApiFactory(final String[] workPackages) throws Exception {
        this();
    }

    public ApiFactory(final String workPackage) throws Exception {
        this(new String[]{workPackage});
    }

    private void initSystemApi() throws Exception {
        ApiClasses
                .forEach(apiClass -> {
                    final ApiComponent component = apiClass.getAnnotation(ApiComponent.class);
                    this.newApiInstance(component, apiClass);
                });
    }

    private void initUserApi() throws Exception {
        workPackages
                .forEach(workPackage -> {
                    final List<String> workClassesName = this.getClassNameByPackage(workPackage);
                    assert workClassesName != null;
                    workClassesName.
                            forEach(workClassName -> {
                                final Class<?> workClass;
                                try {
                                    workClass = Class.forName(workClassName);
                                    final ApiComponent component = workClass.getAnnotation(ApiComponent.class);
                                    this.newApiInstance(component, workClass);
                                } catch (final ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                });

    }

    private void newApiInstance(final ApiComponent component, final Class<?> cls) {
        if (component != null) {
            final String name = component.name();
            if (!ApiMap.containsKey(name)) {
                try {
                    ApiMap.put(component.name(), (AbstractApi) cls.newInstance());
                } catch (IllegalAccessException | InstantiationException ignore) {
                }
            }
        }
    }

    private List<String> getClassNameByPackage(final String packageName) {
        final List<String> classesName = new ArrayList<>();
        final ClassLoader loader = this.getClass().getClassLoader();
        final URL url = loader.getResource(packageName.replace(".", "/"));

        try {
            final File packageDir = new File(new URI(url.getPath()).getPath());
            for (final File classFile : packageDir.listFiles()) {
                String classNickName = classFile.getName();
                classNickName = classNickName.substring(0, classNickName.indexOf('.'));
                classesName.add(packageName + "." + classNickName);
            }
            return classesName;
        } catch (final Exception e) {
            return null;
        }
    }
}
