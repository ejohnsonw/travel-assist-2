package co.travelful.common.json

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonOutput

class JsonUtilities {
    private static ObjectMapper mapper = new ObjectMapper();

    static String toJsonString(HashMap<String,Object> map){
        String json = JsonOutput.toJson(map)
        return JsonOutput.prettyPrint(json)
    }


    static HashMap<String,Object> fromJsonString( String data){
        def hashMap = new groovy.json.JsonSlurper().parseText(data)
        return hashMap
    }

    static HashMap<String,Object> toHashmapFromString( String data){
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
        Map<String,Object> hashMap = mapper.readValue(data, typeRef);
        return hashMap
    }

    static String toStringFromHashmap( HashMap   data){
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(data);
        return jsonResult
    }


    static HashMap<String, Object> toHashmapFromObject(Object obj){
        HashMap<String, Object> data = mapper.convertValue(obj, HashMap.class);
        return data
    }


}
