<#import "common.ftl" as common>
<@common.page title="Login">
    <div>
        <form action="/login" method="post">
            <div>
                Username: <input name="username" type="text">
            </div>
            <div>
                Password: <input name="password" type="password">
            </div>
            <input type="submit" value="Подтвердить">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</@common.page>