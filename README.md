# donotforget


## Prerequisitos

Para empezar a desarrollar, necesitaras las siguientes herramientas: 

* [Git](https://git-scm.com/)
* [Apache Maven](https://maven.apache.org/)
* [AdoptOpenJdk 11 ](https://adoptopenjdk.net/) u otra distribución.

## Aportando al proyecto
### Configurando el entorno

```console
git clone https://github.com/dariovp/donotforget.git
cd donotforget
```

Cambia a la branch development:

```console
git checkout -b development
```

### Aplicando cambios al módulo remote:
```console
mvn -pl remote install
```

### Aplicando cambios a los demás módulos:
```console
mvn -pl <nombre_de _modulo> compile package