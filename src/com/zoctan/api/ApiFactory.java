package com.zoctan.api;

import com.zoctan.api.annotation.ApiComponent;
import com.zoctan.api.impl.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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
        for (final Class<? extends AbstractApi> apiClass : ApiClasses) {
            final ApiComponent component = apiClass.getAnnotation(ApiComponent.class);
            this.newApiInstance(component, apiClass);
        }
    }

    private void initUserApi()
            throws URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (final String workPackage : workPackages) {
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
            if (!ApiMap.containsKey(name)) {
                ApiMap.put(component.name(), (AbstractApi) cls.newInstance());
            }
        }
    }

    private List<String> getClassNameByPackage(final String packageName)
            throws URISyntaxException, NullPointerException {
        final ClassLoader loader = this.getClass().getClassLoader();
        final URL url = loader.getResource(packageName.replace(".", "/"));
        final File packageDir = new File(new URI(url.getPath()).getPath());
        return Arrays.stream(packageDir.listFiles())
                .map(File::getName)
                .map(name -> name.substring(0, name.indexOf('.')))
                .collect(Collectors.toList());
    }
}