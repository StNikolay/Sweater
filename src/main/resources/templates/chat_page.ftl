<#import "common.ftl" as common>
<#include "security.ftl">

<@common.page title="Chat">
    <div class="d-flex align-content-lg-center flex-column bd-highlight mb-3 pl-5" style="height: 600px;">
        <div class="p-2 bd-highlight"></div>
        <div class="mt-auto  p-2 bd-highlight">
            <form action="/chat/${username}/send" method="post">
                <div class="form-group row">
                    <div class="col-sm-8 pl-5">
                        <textarea class="form-control"  name="text" placeholder="Type message"></textarea>
                    </div>
                    <div class="col-sm-2">
                        <input class="btn btn-outline-primary btn-lg" type="submit" value="Send"/>
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</@common.page>