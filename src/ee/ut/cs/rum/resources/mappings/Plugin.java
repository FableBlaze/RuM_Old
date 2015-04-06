package ee.ut.cs.rum.resources.mappings;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Plugin implements Serializable {
	   
    private long id;
    private String name;
    private String totalUses;
    private String lastUsed;
    private String accountsUsed;
    private String statusId;
    private String typeId;
    private String uploaded;
    private String uploaderId;
    private String diabled;
    private String disablerId;
    private String created;
    private String creator;
    private String version;
    private String shortDescription;
    private String longDescription;
    private String parameters;
    private String inputTypeId;
    private String outputTypeId;
    private String location;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotalUses() {
		return totalUses;
	}

	public void setTotalUses(String totalUses) {
		this.totalUses = totalUses;
	}

	public String getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(String lastUsed) {
		this.lastUsed = lastUsed;
	}

	public String getAccountsUsed() {
		return accountsUsed;
	}

	public void setAccountsUsed(String accountsUsed) {
		this.accountsUsed = accountsUsed;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getUploaded() {
		return uploaded;
	}

	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getDiabled() {
		return diabled;
	}

	public void setDiabled(String diabled) {
		this.diabled = diabled;
	}

	public String getDisablerId() {
		return disablerId;
	}

	public void setDisablerId(String disablerId) {
		this.disablerId = disablerId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getInputTypeId() {
		return inputTypeId;
	}

	public void setInputTypeId(String inputTypeId) {
		this.inputTypeId = inputTypeId;
	}

	public String getOutputTypeId() {
		return outputTypeId;
	}

	public void setOutputTypeId(String outputTypeId) {
		this.outputTypeId = outputTypeId;
	}
	
	public String getLocation() {
		return location;
	}

	public void setlocation(String location) {
		this.location = location;
	}

	@Override
    public String toString() {
        return "Plugin [id=" + id + 
        		", mane=" + name + 
        		", totalUses=" + totalUses + 
        		", lastUsed=" + lastUsed + 
        		", accountsUsed=" + accountsUsed + 
        		", statusId=" + statusId + 
        		", typeId=" + typeId + 
        		", uploaded=" + uploaded + 
        		", uploaderId=" + uploaderId + 
        		", diabled=" + diabled + 
        		", disablerId=" + disablerId + 
        		", created=" + created + 
        		", creator=" + creator + 
        		", version=" + version + 
        		", shortDescription=" + shortDescription + 
        		", longDescription=" + longDescription + 
        		", parameters=" + parameters + 
        		", inputTypeId=" + inputTypeId + 
        		", outputTypeId=" + outputTypeId + 
        		", location=" + location + "]";
    }           
}