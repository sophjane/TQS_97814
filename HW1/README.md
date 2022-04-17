API: https://rapidapi.com/api-sports/api/covid-193/



History:

```
https://covid-193.p.rapidapi.com/history?country=portugal&day=2020-04-07
```

Required Parameters: country

Optional Parameters: day

```json
{5 items
    "get":"history"
    "parameters":{2 items
        "country":"portugal"
        "day":"2020-04-07"
    }
    "errors":[0 items
    ]
    "results":2
    "response":[2 items
        0:{8 items
            "continent":NULL
            "country":"Portugal"
            "population":NULL
            "cases":{6 items
                "new":"+712"
                "active":11913
                "critical":271
                "recovered":184
                "1M_pop":"1220"
                "total":12442
            }
            "deaths":{3 items
                "new":"+34"
                "1M_pop":NULL
                "total":345
            }
            "tests":{2 items
                "1M_pop":NULL
                "total":110000
            }
            "day":"2020-04-07"
            "time":"2020-04-07T13:00:05+00:00"
        }
        1:{8 items
            "continent":NULL
            "country":"Portugal"
            "population":NULL
            "cases":{...}6 items
            "deaths":{...}3 items
            "tests":{...}2 items
            "day":"2020-04-07"
            "time":"2020-04-07T11:15:04+00:00"
        }
    ]
}
```

---

Statistics:

```
https://covid-193.p.rapidapi.com/statistics?country=ireland
```

Optional Parameters: country

```json
{5 items
    "get":"statistics"
    "parameters":{1 item
    	"country":"ireland"
    }
    "errors":[0 items
    ]
    "results":1
    "response":[1 item
        0:{8 items
            "continent":"Europe"
            "country":"Ireland"
            "population":5036141
            "cases":{6 items
                "new":NULL
                "active":133367
                "critical":57
                "recovered":1358535
                "1M_pop":"297616"
                "total":1498834
            }
            "deaths":{3 items
                "new":NULL
                "1M_pop":"1376"
                "total":6932
            }
            "tests":{2 items
                "1M_pop":"2375330"
                "total":11962495
            }
            "day":"2022-04-16"
            "time":"2022-04-16T10:00:03+00:00"
        }
    ]
}
```

