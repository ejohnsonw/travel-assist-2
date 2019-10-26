package co.travelful.common.traits


import java.lang.reflect.Field

trait DomainToPojoFactory {
   public  HashMap  buildFrom (Object instance){
       HashMap result = new HashMap()
       def mc = instance.getMetaClass()
       def properties =  instance.getMetaPropertyValues()
       if(instance){
           def fields = instance.class.getDeclaredFields()
           HashMap fieldMap = new HashMap<>()
           fields.each { field ->
               fieldMap.put(field.name,field)
           }
           fields.each { field ->
               try{
                   field.setAccessible(true);
                   def value  = field.get(instance)
                   if(!filtered(field.name)){
                       result[field.name]= value
                       println field.name
                       println "\n *** ${field.type}"

                   }else{
                       if(field.name == "hasMany"){
                           println "hasMany ${value}"
//                       ArrayList manyRelations
//                       if(value instanceof  Collection){
//                           manyRelations = value
//                       }else{
//                           manyRelations = new ArrayList()
//                           manyRelations.add(value)
//                       }
                           for(String relationName : value.keySet()){
                               Object domainType = value.get(relationName)
                               Field fieldRelation = fieldMap.get(relationName)
                               result[relationName] = new ArrayList<>()
                               fieldRelation.setAccessible(true);
                               Set valueMany  = fieldRelation.get(instance)
                               for(Object s : valueMany){
                                   def mapRel = buildFrom(s)
                                   result[relationName].add(mapRel)
                               }
                               println  ""

                           }

                       }
                   }
               }catch(MissingPropertyException mi){
                   println mi.getMessage()
               }

           }
       }

       return result

   }
    private Boolean filtered(String name){
        if(name.contains("\$") || name.startsWith("org_grails") || name == "version"  || name == "metaClass" || name == "version" || name == "hasMany" || name == "constraints"){
            return true
        }
        return false
    }

}
