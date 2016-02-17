# SlupringHTML

entry point = http://codenarc.sourceforge.net/codenarc-rule-index.html 

from the entry point get subURL for the rules and read patternId 

if the patter consists of parameter then get Parameter name and Parameter default value 

and form a json like :
{  
   "name":"codenarc",
   "patterns":[  
      {  
         "patternId":"",
         "level":"",
         "category":""
      },
      {  
         "patternId":"",
         "level":"",
         "category":"",
         "parameters":[  
            {  
               "name":"",
               "default":""
            },
            {  
               "name":"",
               "default":""
            }
         ]
      },
      {  
         "patternId":"",
         "level":"",
         "category":"",
         "parameters":[  
            {  
               "name":"",
               "default":""
            }
         ]
      }
   ]
}
