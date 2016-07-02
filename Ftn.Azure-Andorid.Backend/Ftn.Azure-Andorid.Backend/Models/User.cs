namespace Ftn.Azure_Andorid.Backend.Models
{
    public class User
    {
        public string Email { get; set; }
        public string UserName { get; set; }
        public string Password { get; set; }
        public double Longitude { get; set; }
        public double Lattitude { get; set; }
        public int Id { get; set; }
    }
}