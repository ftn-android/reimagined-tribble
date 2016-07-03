using System;
using System.Collections.Generic;

namespace Ftn.Azure_Andorid.Backend.Models
{
    public class Location
    {
        public int Id { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime StartDate { get; set; }
        public long EndDate { get; set; }
        public bool Type { get; set; }
        public string IncidentType { get; set; }
        public string Author { get; set; }
        public string ConfirmedFrom { get; set; }
        public string UID { get; set; }
        public byte[] ImageData { get; set; }
    }
}