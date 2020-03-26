<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Sign up</title>
    </head>
    <body>
    <@spring.bind "user"/>
        <form action="/join" method="post">
            <div>
                Username: <@spring.formInput "user.username"/>
                <@spring.showErrors "<br>"/>
            </div>
            <div>
                Password: <@spring.formInput "user.password"/>
                <@spring.showErrors "<br>"/>
            </div>
            <div>
                Email: <@spring.formInput "user.email"/>
                <@spring.showErrors "<br>"/>
            </div>
            <input type="submit"/>
        </form>
    </body>
</html>