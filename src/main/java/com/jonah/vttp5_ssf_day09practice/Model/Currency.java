package com.jonah.vttp5_ssf_day09practice.Model;

public class Currency {

            private String currencyId;
            private String currencyName;
            private String currencySymbol;
            private String name;




            

            public Currency() {
            }

            
            public Currency(String currencyId, String currencyName, String currencySymbol, String name) {
                this.currencyId = currencyId;
                this.currencyName = currencyName;
                this.currencySymbol = currencySymbol;
                this.name = name;
            }


            public String getCurrencyId() {
                return currencyId;
            }
            public void setCurrencyId(String currencyId) {
                this.currencyId = currencyId;
            }
            public String getCurrencyName() {
                return currencyName;
            }
            public void setCurrencyName(String currencyName) {
                this.currencyName = currencyName;
            }
            public String getCurrencySymbol() {
                return currencySymbol;
            }
            public void setCurrencySymbol(String currencySymbol) {
                this.currencySymbol = currencySymbol;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }

            


            @Override
            public String toString() {
                return "Currency [currencyId=" + currencyId + ", currencyName=" + currencyName + ", currencySymbol="
                        + currencySymbol + ", name=" + name + "]";
            }


            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((currencyId == null) ? 0 : currencyId.hashCode());
                result = prime * result + ((currencyName == null) ? 0 : currencyName.hashCode());
                result = prime * result + ((currencySymbol == null) ? 0 : currencySymbol.hashCode());
                result = prime * result + ((name == null) ? 0 : name.hashCode());
                return result;
            }


            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                Currency other = (Currency) obj;
                if (currencyId == null) {
                    if (other.currencyId != null)
                        return false;
                } else if (!currencyId.equals(other.currencyId))
                    return false;
                if (currencyName == null) {
                    if (other.currencyName != null)
                        return false;
                } else if (!currencyName.equals(other.currencyName))
                    return false;
                if (currencySymbol == null) {
                    if (other.currencySymbol != null)
                        return false;
                } else if (!currencySymbol.equals(other.currencySymbol))
                    return false;
                if (name == null) {
                    if (other.name != null)
                        return false;
                } else if (!name.equals(other.name))
                    return false;
                return true;
            }

        
    
}
