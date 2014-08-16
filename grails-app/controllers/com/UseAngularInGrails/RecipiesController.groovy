package com.UseAngularInGrails

class RecipiesController {

    static allowedMethods = []

    def recipieslist() {
       def res = new HashMap()
	   def recipielist = Recipies.list()
	   def rec= Recipies.findById(recipielist[0].id)
	   def ingredient=Ingredients.findAllByRecipies(rec)
	   log.debug ingredient
	   res.recipielist = recipielist
	   res.ingredient = ingredient
	   respond res,[formats:['json', 'xml']];
	   return res;
    }
	
	def saveRecipies(){
		log.debug params
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
					if(file1.exists())
					{
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
				obj.errors.each {
					log.debug it
					}
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
		def recipies=Recipies.findById(Long.parseLong(params.id));
		log.debug recipies
		File imageFile=new File(recipies.picpath)
		if(imageFile.exists()){
			byte[] buffer=new FileInputStream(imageFile).getBytes()
			response.setContentLength(buffer.length)
			response.outputStream.write(buffer)
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
}
