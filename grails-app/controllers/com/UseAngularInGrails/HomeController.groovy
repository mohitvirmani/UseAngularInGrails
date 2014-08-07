package com.UseAngularInGrails



class HomeController {

	static allowedMethods = [getAllNews: "GET"]

	def index() {
	}

	def getAllNews(){
		println "List"
		def res = new HashMap()
		def news = News.list();
		println news
		if(news.size()>0){
			res.news =news
			res.status="success"
			res.message="News list coming"
		}else{
		res.status="fails"
		res.message="No News list are coming"
		}
		
		respond res,[formats:['json', 'xml']];
		return res
	}

	def saveNews(){
		println "Add params "+params
		def res = new HashMap()
		if(params){
			News obj = new News();
			if(params?.Heading)
				obj.heading=params?.Heading
			if(params?.Discription)
				obj.descripton=params?.Discription
				def uploadfile =  request.getFile("photo")
				def webRootDir = servletContext.getRealPath("/")
				println uploadfile.originalFilename +"==="+webRootDir
			if(uploadfile.originalFilename){
				obj.pic = uploadfile.originalFilename
				if(obj.save(flush:true)){
					def path = 	webRootDir+"images"+File.separator+"newsImages"+File.separator+obj.id
					File f1 = new File((path))
					if(f1.exists())
					{
						println "exist== "+f1.exists()
					}else{
						println "created "+f1.mkdirs();
					}
					uploadfile.transferTo( new File( path, uploadfile.originalFilename))
				    File f = new File((path+File.separator+uploadfile.originalFilename))
					if(f.exists()) {
						obj.picpath = "images"+File.separator+"newsImages"+File.separator+obj.id+File.separator+uploadfile.originalFilename
						obj.save(flush:true)
						res.status="success"
						res.message="News Successfully Saved"
					}else{
						res.status="success"
						res.message="News Successfully Saved"
					}
				} else{
				    obj.errors.each {print it}
					res.status="success"
					res.message="News Successfully Saved"
				}
			}
		}

		respond res,[formats:['json', 'xml']];
		return res
	}
	
	
	def renderImage(){
		log.debug "render image started"+params.id
		def news=News.findById(params.id);
		if(new File((news.picpath)).exists())
		{
			File imageFile=new File(news.picpath)
			byte[] buffer=new FileInputStream(imageFile).getBytes()
			response.setContentLength(buffer.length)
			response.outputStream.write(buffer)
		}
	}
}