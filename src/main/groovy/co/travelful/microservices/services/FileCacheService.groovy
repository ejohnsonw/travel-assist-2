package co.travelful.microservices.services

import io.micronaut.context.annotation.Value
import org.apache.commons.io.FileUtils

import javax.inject.Singleton

@Singleton
class FileCacheService {
    @Value('${travelful.cache.root}')
    String cacheDir

    @Value('${micronaut.application.name}')
    String appName

    void save(String name, String data, Boolean append) {
        int pos =  "${cacheDir}${appName}/${name}".lastIndexOf("/")
        if(pos > -1){
            String basedir = "${cacheDir}${appName}/${name}".substring(0,pos)
            File f = new File(basedir)
            if(!f.exists()){
                f.mkdirs();
            }
        }
        FileUtils.write(new File("${cacheDir}${appName}/${name}"),data,append)
    }

    String retrieve(String name) {
        def datafile = new File("${cacheDir}${appName}/${name}")
        if(datafile.exists()){
            def data = FileUtils.readFileToString(datafile);
            return data;
        }
        return null;
    }
}
