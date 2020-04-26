<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Sweater</title>
    </head>
    <body>
        <form action="/feed" method="post">
            <div>
                <input name="title" type="text" placeholder="Title" />
                <input type="submit" value="Post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
            <div>
                <input name="text" type="text" placeholder="Text" />
            </div>
        </form>

        <#list userPosts as post>
            <div>
                <div>
                    @${post.author.username}
                </div>
                <div>
                    ${post.title}
                </div>
                <div>
                    ${post.text}
                </div>
            </div>
        </#list>
    </body>
</html>