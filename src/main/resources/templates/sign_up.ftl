<#import "common.ftl" as common>
<@common.page title="Registration">
    <div class="mx-auto " style="width: 300px;">
        <#if message??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <form action="/join" method="post">
            <div class="form-group ">
                <label for="username">Username</label>
                <input name="username" type="text" class="form-control" id="username">

            </div>
            <div class="form-group ">
                <label for="email">Email</label>
                <input name="email" type="email" class="form-control" id="email">

            </div>
            <div class="form-group ">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control" id="password">
            </div>
            <input type="submit" class="btn btn-primary" value="Submit">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </div>
</@common.page>