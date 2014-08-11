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
		println "Add paramss "+params
		def res = new HashMap()
		if(params){
			News obj = new News();
			if(params?.Heading)
				obj.heading=params?.Heading
			if(params?.Discription)
				obj.descripton=params?.Discription
				
		if(params.photo.getOriginalFilename()){
				obj.pic = params.photo.getOriginalFilename()
				if(obj.save(flush:true)){
					def path = 	grailsApplication.config.newsImageLocation+File.separator+obj.id
					File file1 = new File(path)
					if(file1.exists())
					{
						println "File Exist"+file1.exists()
					}else{
						println "File Created "+file1.mkdirs();
					}
					File f=new File(path+File.separator+params.photo.getOriginalFilename())
					InputStream is = params.photo?.getInputStream()
					OutputStream os = new FileOutputStream(path+File.separator+params.photo.getOriginalFilename())   //file path
					byte[] buffer = new byte[params.photo?.getSize()]
					int bytesRead
					while ((bytesRead = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytesRead)
					}
					is.close()
					os.close()
					if(f.exists()) {
						obj.picpath = path+File.separator+params.photo.getOriginalFilename()
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
		def news=News.findById(Long.parseLong(params.id));
		File imageFile=new File(news.picpath)
		if(imageFile.exists())
		{
			byte[] buffer=new FileInputStream(imageFile).getBytes()
			response.setContentLength(buffer.length)
			response.outputStream.write(buffer)
		}
	}
}
