package com.UseAngularInGrails

import grails.converters.JSON

class HomeController {

	static allowedMethods = [getAllNews: "GET",saveEditNewsForm:'POST']

	//First hit, index page, redirected to index.gsp view
	def index() {
		log.debug "index started"
	}
	
	
	def getAllNews(){
		log.debug "getAllNews started"
		def res = new HashMap()
		def news = News.list();
		log.debug "news " + news
		def newlist=[]
		if(news.size()>0){
			news.each {
				def eachnews=[:]
				eachnews.id=it.id
				eachnews.heading=it.heading
				def shortdescrption
				if(it.descripton.length()>250){
					shortdescrption =((it?.descripton)?.substring(0, 247))+"..."
				}else{
				shortdescrption =it?.descripton
				}
				eachnews.descripton=shortdescrption
				eachnews.pic=it.pic
				eachnews.picpath=it.picpath
				newlist.add(eachnews)
			}
			res.news =newlist
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
		log.debug "Add paramss "+params
		def res = new HashMap()
		res = saveNewsToDB(params)
		respond res,[formats:['json', 'xml']];
		return res
	}

	HashMap saveNewsToDB(params){
		if(params){
			def res = new HashMap()
			def count = Integer.parseInt(params.count)
			for(int i=0;i<=count;i++){
				println i
				News obj = new News();

				if(request.getParameter("Heading"+i))
					obj.heading=request.getParameter("Heading"+i)
				if(request.getParameter("Discription"+i))
					obj.descripton=request.getParameter("Discription"+i)

				def fll=request.getFile("photo"+i).getOriginalFilename()
				log.debug(fll)
				if(fll){
					obj.pic = fll

					if(obj.save(flush:true)){
						def path = 	grailsApplication.config.newsImageLocation+File.separator+obj.id
						File file1 = new File(path)
						if(file1.exists())
						{
							println "File Exist"+file1.exists()
						}else{
							println "File Created "+file1.mkdirs();
						}
						File f=new File(path+File.separator+fll)
						InputStream is = request.getFile("photo"+i).getInputStream()
						OutputStream os = new FileOutputStream(path+File.separator+fll)   //file path
						byte[] buffer = new byte[request.getFile("photo"+i).getSize()]
						int bytesRead
						while ((bytesRead = is.read(buffer)) != -1) {
							os.write(buffer, 0, bytesRead)
						}
						is.close()
						os.close()

						if(f.exists()) {
							obj.picpath = path+File.separator+fll
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
			return res
		}
	}

	def renderImage(){
		log.debug "=="+params.id+"=="
		if(params?.id != "{{n" && params?.id != null && params?.id != ""){
			def news=News.findById(Long.parseLong(params.id));
			File imageFile=new File(news.picpath)
			if(imageFile.exists()){
				byte[] buffer=new FileInputStream(imageFile).getBytes()
				response.setContentLength(buffer.length)
				response.outputStream.write(buffer)
			}
			else{
				imageFile=new File("/home/mohit/Desktop/koshishkkdk.jpg")
				byte[] buffer=new FileInputStream(imageFile).getBytes()
				response.setContentLength(buffer.length)
				response.outputStream.write(buffer)
			}
		}
	}
	

	def downloadNewsPic(){
		def news=News.findById(params.id)
		log.debug news
		def path=news.picpath
		File pic=new File (path)
		response.setContentType("application/octet-stream") // or or image/JPEG or text/xml or whatever type the file is
		response.setHeader("Content-disposition", "attachment;filename=\"${news.pic}\"")
		response.outputStream << pic.bytes
}
	def deleteNews(){
		log.debug "params " + params
		News news = News.findById(params.id)
		log.debug "news " + news
		news.delete(failOnError : true)
		def res = new HashMap()
		res.message = "Success"
		respond res,[formats:['json', 'xml']];
		return res;
	}
	
	def saveEditNewsForm(){
		log.debug "saveEditNewsFormSatrted "
		log.debug "params " + params
		News currentlySelectedNews = News.findById(params.id)
		log.debug "currentlySelectedNews " + currentlySelectedNews
		
		log.debug "params.Description " + params.description
		log.debug "params.News " + params.heading
		
		currentlySelectedNews.descripton = params.description
		currentlySelectedNews.heading = params.heading
		if(request.getFile("newsImage").getOriginalFilename()){
		imageUploader(params.newsImage, currentlySelectedNews)
		}
		currentlySelectedNews.save(failOnError : true)
		log.debug "currentlySelectedNews " + currentlySelectedNews
		
		def res = new HashMap()
		
		res.message = "Success"
		
		def newsList = News.list()
		if(newsList[0]?.id){
			res.news = currentlySelectedNews
		}
		respond res,[formats:['json', 'xml']];
		return res;
	}
	
	def imageUploader(def file, News news){
		def outputStreamPath = grailsApplication.config.newsImageLocation
		if(file != null){
			Random randomGenerator = new Random()
			int randomInt = randomGenerator.nextInt(1000000)
			def docName = randomInt+file?.getOriginalFilename()
			news.pic = docName
			news.picpath = outputStreamPath+docName
			news.save(failOnError : true)
			if(file?.getOriginalFilename()){
				InputStream is = file?.getInputStream()
				OutputStream os = new FileOutputStream(outputStreamPath+docName)   //file path
				byte[] buffer = new byte[file?.getSize()]
				int bytesRead
				while ((bytesRead = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead)
				}
				is.close()
				os.close()
				return docName
			}
		}
	}
	
	def moreInfo(){
		log.debug "moreInfo started"
		log.debug "params " + params
		def res = new HashMap()
		if(params.id != 'undefined'){
		News currentlySelectedNews = News.findById(params.id)
		log.debug "currentlySelectedNews " + currentlySelectedNews
		res.currentlySelectedNews = currentlySelectedNews
		}
		res.message = "Success"
		respond res,[formats:['json', 'xml']];
		return res;
	}
	
	def contactDetails(){
		
	}

}