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
		
		
		"/list/recipies(.${format})"(controller:"home", parseRequest:true){
			action = [ POST:"saveNews"]
		}
		
		group("/recipies") {
			"/create.${format}"(controller:"admin", parseRequest:true){
				action = [ POST:"saveProspect"]
			}
			"/assign(.${format})"(controller:"admin", parseRequest:true){
				action = [ POST:"assignProspectToRM"]
			}
			"/list.${format}"(controller:"recipies", parseRequest:true){
				action = [ GET:"recipieslist"]
			}
			"/delete/$id(.${format})"(controller:"admin", parseRequest:true){
				action = [ DELETE:"deleteProspect"]
			}
		}
		
	}
}
