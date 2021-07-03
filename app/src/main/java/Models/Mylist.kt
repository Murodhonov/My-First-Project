package Models

class Mylist {

    var courseName:String = ""
    var description:String = ""
    var className:String = "null"

    constructor(courseName: String, description: String) {
        this.courseName = courseName
        this.description = description
    }

    constructor(courseName: String, description: String, className: String) {
        this.courseName = courseName
        this.description = description
        this.className = className
    }
}