namespace Ftn.Azure_Andorid.Backend.Models
{
    public class HeaderImage
    {
        public int Id { get; set; }
        public byte[] ImageData { get; set; }
        public string ImageName { get; set; }
        public bool IsActive { get; set; }
    }
}