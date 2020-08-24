<#import "common.ftl" as common>
<#include "security.ftl">

<@common.page title="Chat">
    <div class="d-flex align-content-lg-center flex-column bd-highlight mb-3 pl-5" style="height: 600px;">
        <div class="p-2 bd-highlight" style="overflow-y:auto"></div>
        <div data-spy="scroll" style="overflow-y:auto" id="scroll">
            <#list messages as message>
                    <#if message.getSender().getUsername()==name>
                        <div class="d-flex flex-row-reverse bd-highlight">
                            <div class="p-2 w-75 bd-highlight">
                                <div class="card w-75">
                                    <div class="card-body">
                                        ${message.getText()}
                                        <p style="text-align:right;font-size: small">${message.getTime()}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <div class="d-flex flex-row bd-highlight mb-3">
                            <div class="p-2 w-75 bd-highlight">
                                <div class="card w-75">
                                    <div class="card-body">
                                        ${message.getText()}
                                        <p style="text-align:right;font-size: small">${message.getTime()}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
            </#list>
        </div>
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
    <script type="text/javascript">
        const scroll = document.getElementById("scroll");
        hg  = scroll.clientHeight + scroll.scrollTop;
        scroll.scrollTo(0, hg);
    </script>
</@common.page>