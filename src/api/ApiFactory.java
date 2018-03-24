package api;

import api.annotation.ApiComponent;
import api.exception.DuplicationNameException;
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
        this.initSystemTranslator();
        this.initUserTranslator();
    }

    public ApiFactory(final String[] workPackages) throws Exception {
        this();
    }

    public ApiFactory(final String workPackage) throws Exception {
        this(new String[]{workPackage});
    }

    private void initSystemTranslator() throws Exception {
        for (final Class<?> translatorClass : ApiClasses) {
            final ApiComponent component = translatorClass.getAnnotation(ApiComponent.class);
            this.newApiInstance(component, translatorClass);
        }
    }

    private void initUserTranslator() throws Exception {
        for (final String workPackage : workPackages) {
            final List<String> workClassesName = this.getClassNameByPackage(workPackage);
            for (final String workClassName : workClassesName) {
                final Class<?> workClass = Class.forName(workClassName);
                final ApiComponent component = workClass.getAnnotation(ApiComponent.class);
                this.newApiInstance(component, workClass);
            }
        }
    }

    private void newApiInstance(final ApiComponent component, final Class<?> cls) throws Exception {
        if (component != null) {
            final String name = component.name();
            if (ApiMap.containsKey(name)) {
                throw new DuplicationNameException("Name duplication exception");
            } else {
                ApiMap.put(component.name(), (AbstractApi) cls.newInstance());
            }
        }
    }

    private List<String> getClassNameByPackage(final String packageName) throws Exception {
        final List<String> classesName = new ArrayList<>();
        final ClassLoader loader = this.getClass().getClassLoader();
        final URL url = loader.getResource(packageName.replace(".", "/"));

        final File packageDir = new File(new URI(url.getPath()).getPath());
        for (final File classFile : packageDir.listFiles()) {
            String classNickName = classFile.getName();
            classNickName = classNickName.substring(0, classNickName.indexOf('.'));
            classesName.add(packageName + "." + classNickName);
        }
        return classesName;
    }
}
