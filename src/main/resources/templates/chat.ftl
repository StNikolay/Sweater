<#import "common.ftl" as common>
<#include "security.ftl">

<@common.page title="Chat">
    <#if user.isChatEnabled()>
        <div class="container pl-5">
            <form action="/chat/user" method="get">
                <div class="form-group row pl-5">
                    <div class="col-sm-6 pl-5">
                        <input class="form-control" type="text" name="username" placeholder="Type user"/>
                    </div>
                    <div class="col-sm-6">
                <input class="btn btn-outline-primary" type="submit" value="Chat"/>
                    </div>
                </div>
            </form>

        </div>
    <#else>
        <form action="chat/registration" method="post">
            <input class="nav-link" type="submit" value="Registration in chat"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </#if>
</@common.page>