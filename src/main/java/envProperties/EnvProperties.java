package envProperties;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("env.properties") //для иницализации класса будет использоваться файл env.properties
public class EnvProperties {

    public EnvProperties() {
        PropertyLoader.populate(this); //инициализация полей класса значениями из файла
    }

    @Property("env.test") //ключ проперти, по которой переменной будет выставлено значение
    private String env;

    public String getEnv() {
        return env;
    }
}
