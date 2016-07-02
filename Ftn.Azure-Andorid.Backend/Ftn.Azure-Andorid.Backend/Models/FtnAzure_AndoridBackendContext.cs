using System.Data.Entity;

namespace Ftn.Azure_Andorid.Backend.Models
{
    public class FtnAzure_AndoridBackendContext : DbContext
    {
        // You can add custom code to this file. Changes will not be overwritten.
        // 
        // If you want Entity Framework to drop and regenerate your database
        // automatically whenever you change your model schema, please use data migrations.
        // For more information refer to the documentation:
        // http://msdn.microsoft.com/en-us/data/jj591621.aspx
    
        public FtnAzure_AndoridBackendContext() : base("name=DefaultConnection")
        {
        }

        public System.Data.Entity.DbSet<Ftn.Azure_Andorid.Backend.Models.User> Users { get; set; }

        public System.Data.Entity.DbSet<Ftn.Azure_Andorid.Backend.Models.Location> Locations { get; set; }

        public DbSet<HeaderImage> HeaderImages { get; set; }
    }
}
