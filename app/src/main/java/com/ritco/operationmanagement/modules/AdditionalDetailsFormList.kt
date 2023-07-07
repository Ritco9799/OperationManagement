package com.ritco.operationmanagement.modules

data class AdditionalDetailsFormList(var showForm:Boolean=false,var formComplite:Boolean=false,var openForm:Boolean=false,var formData: AFormData)

data class AFormData(var remark:String,
                    var AdditionalCharges:String,
                    var AddChargRemarks:String,)
