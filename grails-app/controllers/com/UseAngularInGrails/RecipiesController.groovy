package com.UseAngularInGrails

import org.springframework.util.ConcurrentReferenceHashMap.Entries;

class RecipiesController {

	static allowedMethods = []

	def recipieslist() {
		def res = new HashMap()
		def recipielist = Recipies.list()
		if(recipielist[0]?.id){
			def rec= Recipies.findById(recipielist[0]?.id)
			def ingredient
			if(rec){
				ingredient=Ingredients.findAllByRecipies(rec)
			}
			res.ingredient = ingredient
			log.debug ingredient
		}
		
		res.recipielist = recipielist
		respond res,[formats:['json', 'xml']];
		return res;
	}

	def saveRecipies(){
		log.debug "saveRecipes started"
		log.debug "params " + params
		def res = new HashMap()
		if(params){
			def count =Integer.parseInt(params.count)
			log.debug count
			Recipies obj = new Recipies()

			if(params?.Name)
				obj.name = params?.Name

			if(params?.Discription)
				obj.description = params?.Discription

			if(count != null){
				for(int i =0;i<=count;i++){
					Ingredients ingredients = new Ingredients()
					if(request.getParameter("IName"+i))
						ingredients.ingredientName = request.getParameter("IName"+i)
					if(request.getParameter("IAmount"+i))
						ingredients.amount = Integer.parseInt(request.getParameter("IAmount"+i))
					if(request.getParameter("IAmountUnits"+i))
						ingredients.amountUnits = request.getParameter("IAmountUnits"+i)
					obj.addToIngredients(ingredients)
				}
				if(obj.save(flush:true)){
					def filename=request.getFile("photo").getOriginalFilename()
					log.debug(filename)
					def path = 	grailsApplication.config.recipieImageLocation+File.separator+obj.id
					File file1 = new File(path)
					if(file1.exists()) {
						log.debug "File Exist"+file1.exists()
					}else{
						log.debug "File Created "+file1.mkdirs();
					}
					File f=new File(path+File.separator+filename)
					InputStream is = request.getFile("photo").getInputStream()
					OutputStream os = new FileOutputStream(path+File.separator+filename)   //file path
					byte[] buffer = new byte[request.getFile("photo").getSize()]
					int bytesRead
					while ((bytesRead = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytesRead)
					}
					is.close()
					os.close()

					if(f.exists()) {
						obj.picpath = path+File.separator+filename
						obj.pic = filename
						obj.save(flush:true)
						res.status=  "success"
						res.message= "Recipie Successfully Saved with file"
					}else{
						res.status="success"
						res.message="Recipie Successfully Saved without file "
					}
				}else{
					obj.errors.each { log.debug it }
					res.message = "Cannot Save Recipie Data"
					res.status = "Failed"
				}
			}else{
				res.message = "Cannot Save Recipie Data"
				res.status = "Failed"
			}
		}else{
			res.message = "Cannot Save Recipie Data"
			res.status = "Failed"
		}

		respond res,[formats:['json', 'xml']];
		return res;
	}


	def renderImage(){
		if(params?.id){
		def recipies=Recipies.findById(Long.parseLong(params.id));
		log.debug recipies
		File imageFile=new File(recipies.picpath)
		if(imageFile.exists()){
			byte[] buffer=new FileInputStream(imageFile).getBytes()
			response.setContentLength(buffer.length)
			response.outputStream.write(buffer)
		}
		}
	}

	def ingredientsList(){
		log.debug params.id
		def res = new HashMap();
		Recipies recipies =Recipies.findById(Long.parseLong(params.id))
		def ingredients = Ingredients.findAllByRecipies(recipies)
		log.debug ingredients
		res.ingredients=ingredients
		respond res,[formats:['json', 'xml']];
		return res;
	}

	def editRecipies(){
		log.debug params.id
		def recipies=Recipies.findById(Long.parseLong(params.id));
		def ingredients = Ingredients.findAllByRecipies(recipies)
		def res = new HashMap();
		res.ingredients=ingredients
		res.recipies=recipies
		respond res,[formats:['json', 'xml']];
		return res;
	}

	def deleteRecipie(){
		log.debug params.id
		def recipies
		def ingredients
		def message
		def res = new HashMap()
		recipies=Recipies.findById(Long.parseLong(params.id));

		if(recipies){
			ingredients= Ingredients.findAllByRecipies(recipies)
			if(ingredients.size()>0){
				ingredients.each {
					log.debug it
					it.delete(flush:true)
				}
				def 	isingredients= Ingredients.findAllByRecipies(recipies)
				if(isingredients){
					log.debug "Cannot delete Ingredients"
				}else{
					recipies.delete(flush:true)
					def path=grailsApplication.config.recipieImageLocation+File.separator+params.id
					def status=deleteDirectory(path)
					if(status){
						log.debug "Directoy Deleted"
						res.message = "Recipies Deleted and Directory also"
					}else{
						res.message  = "Recipies Deleted and Not Directory"
					}
				}
			}else{
				log.debug "No ingredients found for this recipies"
			}
		}
		else{
			res.message  = "No recipies found"
		}
		respond res,[formats:['json', 'xml']];
		return res;
	}

	boolean  deleteDirectory(def path){
		File file = new File(path)
		if(file.exists()){
			log.debug " file exists"
			File[] entries =file.listFiles()
			for(int i=0;i<entries.length;i++){
				if(entries[i].isDirectory()) {
					deleteDirectory(entries[i]);
				}
				else {
					entries[i].delete();
				}
			}
		}else{
			log.debug "No file exists"
		}
		return (file.delete())
	}
}
