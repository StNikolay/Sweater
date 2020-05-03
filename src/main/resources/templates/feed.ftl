<#import "common.ftl" as common>
<@common.page title="Sweater">
    <form action="/feed" method="post">
        <div class="input-group">
            <input class="input-group-text" name="title" type="text" placeholder="Title">
            <div class="input-group-append">
                <input class="btn btn-outline-primary btn-lg" type="submit" value="Post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>
        <div class="form-group w-75">
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="text" placeholder="Text"></textarea>
        </div>
    </form>


    <#list userPosts as post>
        <br>
        <div class="card w-75">
            <div class="card-header">
                @${post.author.username}
            </div>
            <div class="card-body">
                <h5 class="card-title">
                    ${post.title}
                </h5>
                <p class="card-text">
                    ${post.text}
                </p>
            </div>
        </div>
    </#list>
</@common.page>