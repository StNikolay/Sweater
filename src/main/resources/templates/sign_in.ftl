<#import "common.ftl" as common>
<@common.page title="Login">
    <div class="mx-auto " style="width: 300px;">
        <form action="/login" method="post">
            <div class="form-group ">
                <label for="username">Username</label>
                <input class="form-control" name="username" type="text" id="username">
            </div>
            <div class="form-group ">
                <label for="password">Password</label>
                <input class="form-control" name="password" type="password" id="password">
            </div>
            <input type="submit" value="Submit" class="btn btn-primary">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <a class="btn btn-primary" href="/join">Sign up</a>
        </form>
    </div>
</@common.page>