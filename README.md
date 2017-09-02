Exploring Kotlin DSL

*Create a DSL to express JSON in Kotlin and output the result to actual JSON*

json {    
         obj {  
            "description" to "sample"  
            "active"      to true                
            "age"         to 12  
            "email"       to null  
      }  
  }  
  
will render as  

{  
 "description" : "sample"  
 "active" : true  
 "email" : null  
 "age" : 12  
}  