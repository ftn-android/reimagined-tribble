#Interface Document - Rest flavour

User
Post User - Signup
returns User

GetUsers(string username, string password) - Login
returns list of users, if empty client handles as login failed...
if username and password is empty, return all users


AddIncident or AddGasStation => PostLocation(Location)

GetLocations(string filterByType, boolean sendPicturesBack, double? longitude = null, double? latidude = null, double? radius = null)
filterByType == 'incident' => incident
filterByType == 'all' => both
else => gas station
if all 3 coordinate param is present, it will filter the objects by location

see other CRUD operations of needed.

#Install service

You'll need Visual Studio 2015 with Azure Developer kit.
You don't need to have an Azure account set up, you may start the service locally.
