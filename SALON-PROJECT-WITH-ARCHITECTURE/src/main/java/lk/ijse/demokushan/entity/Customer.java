package lk.ijse.demokushan.entity;


    public class Customer {
        private String customerId;
        private String name;
        private String phoneNumber;
        private String address;
        private String email;
        private String status;

        public Customer() {

        }

        public Customer(String customerId, String name, String phoneNumber, String address, String email, String status) {
            this.customerId = customerId;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.email = email;
            this.status = status;
        }


        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
