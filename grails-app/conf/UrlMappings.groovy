class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"index")
        "500"(view:'/error')
		
		
		"/getNewslist(.${format})"(controller:"home", parseRequest:true){
			action = [ GET:"getAllNews"]
		}
		
		"/uploadNews/record(.${format})"(controller:"home", parseRequest:true){
			action = [ POST:"saveNews"]
		}
		
		
		
	}
}
