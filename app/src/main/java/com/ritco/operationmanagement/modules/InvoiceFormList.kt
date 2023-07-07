package com.ritco.operationmanagement.modules

data class InvoiceFormList(var showForm:Boolean=false,var formComplite:Boolean=false,var openForm:Boolean=false,var formData: FormData)

data class FormData(var InvoiceNo:String,
                    var InvoiceDt:String,
                    var E_wayBillNo:String,
                    var DONo:String,
                    var SaidToContain:String,
                    var CustomerRefNo:String,
                    var TypesOfPackages:String,
                    var NoOfPackages:String,
                    var DeclaredValue:String,
                    var ActualWt:String,
                    var ChargeWt:String,
                    var TotalActualWt:Int,
                    var TotalChargedWt:Int,
                    var TotalActualWt_Back:Int,
                    var TotalChargedWt_Back:Int,)
