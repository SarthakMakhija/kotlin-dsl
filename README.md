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
In order to include nested objects -

```
json {  
    root {
      "description" to "sample"  
      "active"      to true  
      "age"         to 12  
      "employee"    to obj {  
          "name"    to "sample"  
          "department" to "HR"  
          "address" to obj {  
            "country": "India"
          }
      }      
    }  
}  
```

will render as 

```
{  
 "description" : "sample"  
 "active" : true  
 "age" : 12  
 "employee" : {  
    "name" : "sample"  
    "department" : "HR"  
    "address" : {
        "country" : "India"  
    }    
 }
}  
```
