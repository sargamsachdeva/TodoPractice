package todo

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured


@Secured('ROLE_USER')
class UserController {

    SpringSecurityService springSecurityService

    def index() {

        if(isLoggedIn()){

           // String email = authenticatedUser.email
            String username= getPrincipal().username
            String password=getAuthenticatedUser().password
            println("username in user index;;;;;;;;;;;;;;"+username)
            println("password in user index;;;;;;;;;"+password)

            render(view: 'index1', model: [loggedInEmail:  getPrincipal().username,loggedInPassword:password])
        }
    }

    def login(){
        def obj = request.JSON
        println("email:-"+obj.email)
        println("---------------")
        println("password:-"+obj.password)

        session.email = obj.email
        session.password=obj.password

       // response.sendError(500)

        render(["success": true, email: obj.email,password:obj.password] as JSON)

        //response<<"success"
        //forward(controller: 'toDo', action: 'index',params:[email:email])
    }


    def logout(String email){

        println("logoutt")

        println("!email--->"+!email)
        session.email = null
        render(["success": true,email: session.email] as JSON)
    }

   def login1(String email){

       if(isLoggedIn()){

           // String email = authenticatedUser.email
           String username= getPrincipal().username
           println("username;;;;;;;;;;;;;;"+username)

           email = getPrincipal().username
                   // session.email = email

           println("password in login1;;;;;;;;;"+getAuthenticatedUser().password)

       render (view:'login1',model: [loggedInEmail: getPrincipal().username,loggedInPassword:getAuthenticatedUser().password])
   }}
    def dashboard(){

        render(view:'dashboard')
    }
    def getLoggedInEmail(){

        render([email: getAuthenticatedUser().username?: 'null' ] as JSON)  //email h username ki jgh?
    }
}
