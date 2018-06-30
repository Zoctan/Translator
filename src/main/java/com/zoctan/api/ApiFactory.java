package com.zoctan.api;

import com.zoctan.api.annotation.ApiComponent;
import com.zoctan.api.impl.GoogleApi;
import com.zoctan.api.impl.KingSoftApi;
import com.zoctan.api.impl.OmiApi;
import com.zoctan.api.impl.YoudaoApi;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * API工厂
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class ApiFactory {
    private final Map<String, AbstractApi> ApiMap = new HashMap<>();
    private final List<String> workPackages = new ArrayList<>();

    private final List<Class<? extends AbstractApi>> ApiClasses = Arrays.asList(
            OmiApi.class,
            YoudaoApi.class,
            GoogleApi.class,
            KingSoftApi.class);

    public AbstractApi get(final String name) {
        return this.ApiMap.get(name);
    }

    public Map<String, AbstractApi> getApiMap() {
        return this.ApiMap;
    }

    public ApiFactory() {
        try {
            this.initSystemApi();
            this.initUserApi();
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ApiFactory(final String[] workPackages) {
        this();
    }

    public ApiFactory(final String workPackage) {
        this(new String[]{workPackage});
    }

    private void initSystemApi()
            throws InstantiationException, IllegalAccessException {
        for (final Class<? extends AbstractApi> apiClass : this.ApiClasses) {
            final ApiComponent component = apiClass.getAnnotation(ApiComponent.class);
            this.newApiInstance(component, apiClass);
        }
    }

    private void initUserApi()
            throws URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (final String workPackage : this.workPackages) {
            final List<String> workClassesName = this.getClassNameByPackage(workPackage);
            for (final String workClassName : workClassesName) {
                final Class<?> workClass = Class.forName(workClassName);
                final ApiComponent component = workClass.getAnnotation(ApiComponent.class);
                this.newApiInstance(component, workClass);
            }
        }
    }

    private void newApiInstance(final ApiComponent component, final Class<?> cls)
            throws InstantiationException, IllegalAccessException {
        if (component != null) {
            final String name = component.name();
            if (!this.ApiMap.containsKey(name)) {
                this.ApiMap.put(component.name(), (AbstractApi) cls.newInstance());
            }
        }
    }

    private List<String> getClassNameByPackage(final String packageName)
            throws URISyntaxException {
        final ClassLoader loader = this.getClass().getClassLoader();
        final URL url = loader.getResource(packageName.replace(".", "/"));

        final File packageDir = new File(new URI(Objects.requireNonNull(url).getPath()).getPath());
        return Arrays.stream(Objects.requireNonNull(packageDir.listFiles()))
                .map(File::getName)
                .map(name -> name.substring(0, name.indexOf('.')))
                .collect(Collectors.toList());
    }
}
