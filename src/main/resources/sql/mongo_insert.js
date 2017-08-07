db = connect("localhost:27017/test");
db.driverResult.insert([
	{"_class" : "com.krekapps.indycarstats.models.DriverResult", "carNum" : 5, "startPos" : 6, "endPos" : 6, "driverName" : "Mike Groff", "raceName" : "Indy 200 at Walt Disney World race", "status" : "Mech" }
    {}
])