## Exploring Kotlin DSL

*Basic DSL to express JSON in Kotlin and output the result to actual JSON*

```
json {  
    root {
      "description" to "sample"  
      "active"      to true  
      "age"         to 12  
    }  
}  
```
will render as  

```
{  
 "description" : "sample"  
 "active" : true  
 "age" : 12  
}  
```
