class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"index")
        "500"(view:'/error')
		
		//Home Controller
		"/getNewslist(.${format})"(controller:"home", parseRequest:true){
			action = [ GET:"getAllNews"]
		}
		
		"/uploadNews/record(.${format})"(controller:"home", parseRequest:true){
			action = [ POST:"saveNews"]
		}
		
		"/editNews/edit(.${format})"(controller:"home", parseRequest:true){
			action = [ POST:"saveEditNewsForm"]
		}
		
		"/home/contactDetails(.${format})"(controller:"home", parseRequest:true){
			action = [ GET:"contactDetails"]
		}
		
		//Recipe Controller
		"/getRecipielist(.${format})"(controller:"recipies", parseRequest:true){
			action = [ GET:"recipieslist"]
		}
		
		"/getIngredientslist/$id(.${format})"(controller:"recipies", parseRequest:true){
			action = [ GET:"ingredientsList"]
		}
		
		"/recipies/create(.${format})"(controller:"recipies", parseRequest:true){
				action = [POST:"saveRecipies"]
			}
			
		"/editRecipie/$id(.${format})"(controller:"recipies", parseRequest:true){
			action = [POST:"editRecipies"]
		}
		
		"/deleteRecipie/$id(.${format})"(controller:"recipies", parseRequest:true){
			action = [POST:"deleteRecipie"]
		}
		
	}
}
