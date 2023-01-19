
# Love Calculator
- Web App that calculates the compatibility of two names
- Compatible score is obtained from an external API


## `RedisConfig.java` + `application.properties` + `pom.xml` files

- As discussed, these files are largely similar to previous redis-jedis projects
- This app uses `<String, String>` serialization for redis values


## CompatibilityDao.java

- DAO is short for Data Access Object i.e. an object used to access data
- This should be named DTO (Data Transfer Object), but oh well :shrug:
- This DAO is used to hold JSON data from the API call, and is passed to model for Thymeleaf processing


## Tidbits

- Headers are injected to the http-request using `RequestEntity` **as shown in day17 slides**. This is different from what was suggested to us over Slack. **Highly recommend you follow how it's done in the slides (also used in this application)**

- `loveService.toObject` methods converts JSON-String to CompatibilityDao object via Jackson, not with `JsonReader` that was showed in class. If this confuses you, do what we were taught in class. Ignore my implementation. 

- `result.html` contains thymeleaf-if-statements (ternary operator) to display "Compatible" / "Not Compatible" based on percentage. By now, you're all proficient enough to know where thymeleaf syntax goes (only in html files!), and are all comfortable with the usual thymeleaf expressions. **This is good time to add thymeleaf-conditionals into your toolbox, in case you need it in assessment.**


## Final Remarks

This is a good exercise to practice most of what we've learned so far. It lacks (1) JSON building, (2) Rest Controller, and (3) http-sessions, but otherwise the scope is appropriate and the implementation not too complex. 

This workshop tests your understanding of MVC structure / flow. If you can do this by yourself (with google + slides, but w/o referring to git repo), you're doing well. If you can't, this is a good workshop to study. -- GuyAtTheFront