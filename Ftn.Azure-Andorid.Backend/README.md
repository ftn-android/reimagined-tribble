Interface Document - Rest flavour

User
Post User - Signup
returns User

GetUsers(string username, string password) - Login
returns list of users, if empty client handles as login failed... (worry)
//swagger 2.0 has some nice restrictions..

SetSettingsForUser - PutUser(User)


AddIncident or AddGasStation => PostLocation(Location)

GetLocations(bool incident, double? longitude = null, double? latidude = null, double? radius = null)
incident = true => incident
incident = false => gasStation
if all 3 optional param is present, it will filter the objects by location

see other CRUD operations of needed.

GetNearestGasStation - TODO
[12:31:20 AM] Kovács József: TODO 2 => define settings for user