## Exploring Kotlin DSL

*Basic DSL to express JSON in Kotlin and output the result to actual JSON*

```
json {  
    obj {  
      "description" to "sample"  
      "active"      to true  
      "email"       to null  
      "age"         to 12  
    }  
}  
```
will render as  

```
{  
 "description" : "sample"  
 "active" : true  
 "email" : null  
 "age" : 12  
}  
```
