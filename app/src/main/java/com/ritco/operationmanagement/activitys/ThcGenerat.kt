package com.ritco.operationmanagement.activitys



import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ritco.om.R
import com.ritco.om.databinding.ActivityThcGeneratBinding
import com.ritco.operationmanagement.adaptor.AdditionalDetailsAdaptor
import com.ritco.operationmanagement.adaptor.InvoiceFormAdaptor
import com.ritco.operationmanagement.botemSheet.PodDetails
import com.ritco.operationmanagement.botemSheet.VehicalDetails
import com.ritco.operationmanagement.interfaces.InvoiceForm
import com.ritco.operationmanagement.ktApplication.BarScanner
import com.ritco.operationmanagement.modules.AFormData
import com.ritco.operationmanagement.modules.AdditionalDetailsFormList
import com.ritco.operationmanagement.modules.FormData
import com.ritco.operationmanagement.modules.InvoiceFormList


class ThcGenerat : AppCompatActivity() , InvoiceForm {
    lateinit var binding: ActivityThcGeneratBinding
    private  val OP_SCANNER = 1001
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // Handle the Intent
        }
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_thc_generat)
        val view = binding.root
        setContentView(view)

      inst()
    }
    fun inst()
    {


setSpiner()
        setSmartsearch()
        setInvoiceData(false,false)
        AdditionalDetails(false,false)
    }
   fun setSpiner()
   {
       val years = arrayOf("items1", "it2ems", "items3", "items5")
       val langAdapter = ArrayAdapter<CharSequence>(this, R.layout.spinner_text, years)
       langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown)
       binding.spinnerLoadType?.setAdapter(langAdapter)
       binding.spinnerSlabType?.setAdapter(langAdapter)
       binding.spinnerSlabType?.setAdapter(langAdapter)
       binding.spinnerRouteMode?.setAdapter(langAdapter)
       binding.spinnerPermittedBy?.setAdapter(langAdapter)
       binding.spinerDrLicenseType?.setAdapter(langAdapter)
       binding.spinerFrLoadType?.setAdapter(langAdapter)
       binding.spinerBalancePayableAt?.setAdapter(langAdapter)




   }
    fun setSmartsearch()
    {
        val countries = arrayOf(
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
            "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
            "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria",
            "Burkina Faso", "Burma (Myanmar)", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
            "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
            "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica",
            "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
            "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia",
            "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
            "Greenland", "Grenada", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
            "Haiti", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica",
            "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait",
            "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
            "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia",
            "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico",
            "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
            "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia",
            "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea",
            "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",
            "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
            "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda",
            "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin",
            "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
            "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea",
            "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland",
            "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau",
            "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
            "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
       // binding.consignor.threshold = 3
        binding.consignor.setAdapter(adapter)
        binding.consigneeType.setAdapter(adapter)
        binding.otherType.setAdapter(adapter)
        binding.fromCity.setAdapter(adapter)
        binding.toCity.setAdapter(adapter)
        binding.thcFromState.setAdapter(adapter)
    }

    fun AdditionalDetails(isShow:Boolean,openForm:Boolean)
    {
      var  layoutManager:LinearLayoutManager= LinearLayoutManager(this)
        var invoiceList: ArrayList<AdditionalDetailsFormList>? = ArrayList()
        var formData: AFormData =AFormData("",",",",")
         var data:AdditionalDetailsFormList=AdditionalDetailsFormList(isShow,false,openForm,formData)
        invoiceList?.add(data)
      var  invoiceAdapror: AdditionalDetailsAdaptor = AdditionalDetailsAdaptor(this, invoiceList!! as ArrayList<AdditionalDetailsFormList>,this)
        binding?.AdditionalView?.layoutManager  =layoutManager
        binding?.AdditionalView?.adapter = invoiceAdapror
        invoiceAdapror.notifyDataSetChanged()
    }

    fun setInvoiceData(isShow:Boolean,openForm:Boolean)
    {
        var  layoutManager:LinearLayoutManager= LinearLayoutManager(this)
        var invoiceList: ArrayList<InvoiceFormList>? = ArrayList()
        var formData: FormData=FormData("",",",",",",",",","","","","","","",0,0,0,0)
        var data:InvoiceFormList=InvoiceFormList(isShow,false,openForm,formData)
        invoiceList?.add(data)
        var  invoiceAdapror:InvoiceFormAdaptor = InvoiceFormAdaptor(this, invoiceList!! as ArrayList<InvoiceFormList>,this)
        binding?.ivvoiceView?.layoutManager  =layoutManager
        binding?.ivvoiceView?.adapter = invoiceAdapror
    }






    fun bill_next(v:View)
    {
        var vId:Int=v.id
        if(vId==R.id.bil_next) {

            if ( billFormIsDone()) {
                binding.billTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.billTitle?.setTextColor(getColor(R.color.blue));
                binding.billLayout.visibility = View.GONE
               // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
                binding.docketLayout?.visibility = View.VISIBLE
                binding.docketTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.two_icon),
                    null, getResources().getDrawable(R.drawable.down_arrow_icon),
                    null)
            }
            else{
                Toast.makeText(this,"Please fill to Billing Form",Toast.LENGTH_LONG).show()
            }



        }
        else{

                if(billFormIsDone()) {
                    if(binding.billLayout?.isVisible==true) {

                        binding.billTitle?.setCompoundDrawablesWithIntrinsicBounds(
                            getResources().getDrawable(R.drawable.done_mark_icon),
                            null,
                            getResources().getDrawable(R.drawable.next_form_icon_blue),
                            null
                        )
                        binding.billLayout?.visibility = View.GONE
                    }
                    else{
                        binding.billTitle?.setCompoundDrawablesWithIntrinsicBounds(
                            getResources().getDrawable(R.drawable.done_mark_icon),
                            null,
                            getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                            null
                        )
                        binding.billLayout?.visibility = View.VISIBLE
                        binding.docketLayout?.visibility = View.GONE
                    }

                }

                //TransitionManager.beginDelayedTransition(binding.billLayout!!, AutoTransition())


        }
    }

    fun nextDocket(v:View)
    {



        var vId:Int=v.id
        if(vId==R.id.docet_next) {

            if ( docketIsDone()) {
                binding.docketTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.docketTitle?.setTextColor(getColor(R.color.blue));
                binding.docketLayout.visibility = View.GONE
                // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
                setInvoiceData(true,true)

            }
            else{
                Toast.makeText(this,"Please fill to Docket Form",Toast.LENGTH_LONG).show()

            }


        }
        else{


                if(docketIsDone()) {
                    if(binding.docketLayout?.isVisible==true) {

                        binding.docketTitle?.setCompoundDrawablesWithIntrinsicBounds(
                            getResources().getDrawable(R.drawable.done_mark_icon),
                            null,
                            getResources().getDrawable(R.drawable.next_form_icon_blue),
                            null
                        )
                        binding.docketLayout?.visibility = View.GONE
                    }
                    else{

                        binding.docketTitle?.setCompoundDrawablesWithIntrinsicBounds(
                            getResources().getDrawable(R.drawable.done_mark_icon),
                            null,
                            getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                            null
                        )
                        binding.docketLayout?.visibility = View.VISIBLE
                        binding.billLayout?.visibility = View.GONE
                    }


                }



        }


    }
    fun thcNext(v:View)
    {




        var vId:Int=v.id
        if(vId==R.id.nextThc) {

            if ( thcIsDone()) {
                binding.thcTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.thcTitle?.setTextColor(getColor(R.color.blue));
                binding.thcLayout.visibility = View.GONE

                // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
//                 setInvoiceData(true,true)
                binding.brokerTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.six_icon),
                    null, getResources().getDrawable(R.drawable.down_arrow_icon),
                    null)
               // binding.brokerTitle?.setTextColor(getColor(R.color.blue));
                binding.brokerLayout.visibility = View.VISIBLE


            }
            else{
                Toast.makeText(this,"Please fill to THC Form",Toast.LENGTH_LONG).show()

            }


        }
        else{


            if(docketIsDone()) {
                if(binding.thcLayout?.isVisible==true) {

                    binding.thcTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.next_form_icon_blue),
                        null
                    )
                    binding.thcLayout?.visibility = View.GONE
                }
                else{

                    binding.thcTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                        null
                    )
                    binding.thcLayout?.visibility = View.VISIBLE
                    //  binding.billLayout?.visibility = View.GONE
                }


            }



        }




    }





    fun thcIsDone():Boolean
    {
        if(TextUtils.isEmpty(binding?.thcVehicalNo?.text.toString()))
        {
            Log.e("docketNo","docketNo False")
            return false
        }
        if(TextUtils.isEmpty(binding?.thcVehicalstates?.text.toString()))
        {
            Log.e("docketDate","docketDate False")
            return false
        }
        if(TextUtils.isEmpty(binding?.ThcVehicleDetails?.text.toString()))
        {
            Log.e("address","address False")
            return false
        }
        if(TextUtils.isEmpty(binding?.thcFromState?.text.toString()))
        {
            Log.e("gst","gst False")
            return false
        }
        if(TextUtils.isEmpty(binding?.thcFromCity?.text.toString()))
        {
            Log.e("fromCity","fromCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.thcToCity?.text.toString()))
        {
            Log.e("fromState","fromState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.thcSealNo?.text.toString()))
        {
            Log.e("toCity","toCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.thcTransitDays?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        val text: String = binding.thcDeliveryDateTime.text.toString()
        if(TextUtils.isEmpty(text.trim()))
        {
            Log.e("spinnerLoadType","spinnerLoadType False")
            return false
        }

        val text_one: String = binding.thcPoDDetails.toString()
        if(TextUtils.isEmpty(text_one.trim()))
        {
            Log.e("spinnerSlabType","spinnerSlabType False")
            return false
        }



        return true
    }
   fun docketIsDone():Boolean
   {
       if(TextUtils.isEmpty(binding?.docketNo?.text.toString()))
       {
           Log.e("docketNo","docketNo False")
           return false
       }
       if(TextUtils.isEmpty(binding?.docketDate?.text.toString()))
       {
           Log.e("docketDate","docketDate False")
           return false
       }
       if(TextUtils.isEmpty(binding?.address?.text.toString()))
       {
           Log.e("address","address False")
           return false
       }
       if(TextUtils.isEmpty(binding?.gst?.text.toString()))
       {
           Log.e("gst","gst False")
           return false
       }
       if(TextUtils.isEmpty(binding?.fromCity?.text.toString()))
       {
           Log.e("fromCity","fromCity False")
           return false
       }
       if(TextUtils.isEmpty(binding?.fromState?.text.toString()))
       {
           Log.e("fromState","fromState False")
           return false
       }
       if(TextUtils.isEmpty(binding?.toCity?.text.toString()))
       {
           Log.e("toCity","toCity False")
           return false
       }
       if(TextUtils.isEmpty(binding?.toState?.text.toString()))
       {
           Log.e("toState","toState False")
           return false
       }
       val text: String = binding.spinnerLoadType?.getSelectedItem().toString()
       if(TextUtils.isEmpty(text.trim()))
       {
           Log.e("spinnerLoadType","spinnerLoadType False")
           return false
       }

       val text_one: String = binding.spinnerSlabType?.getSelectedItem().toString()
       if(TextUtils.isEmpty(text_one.trim()))
       {
           Log.e("spinnerSlabType","spinnerSlabType False")
           return false
       }
       val text_two: String = binding.spinnerRouteMode?.getSelectedItem().toString()
       if(TextUtils.isEmpty(text_two.trim()))
       {
           Log.e("spinnerRouteMode","spinnerRouteMode False")
           return false
       }
       val selectedId: Int = binding.transhipmentGrop.getCheckedRadioButtonId()
       val radioButton = binding.root.findViewById<RadioButton>(selectedId)
       if(TextUtils.isEmpty(radioButton.text.toString().trim()))
       {
           Log.e("transhipmentGrop","transhipmentGrop False")
           return false
       }
       if(TextUtils.isEmpty(binding.transhipmentLocation.text.toString().trim()))
       {
           Log.e("transhipmentLocation","transhipmentLocation False")
           return false
       }
       if(TextUtils.isEmpty(binding.transhipmentCity.text.toString().trim()))
       {
           Log.e("transhipmentCity","transhipmentCity False")
           return false
       }

       if(TextUtils.isEmpty(binding.tentativeRate.text.toString().trim()))
       {
           Log.e("tentativeRate","tentativeRate False")
           return false
       }

       val text_three: String = binding.spinnerPermittedBy?.getSelectedItem().toString()
       if(TextUtils.isEmpty(text_three.trim()))
       {
           Log.e("spinnerPermittedBy","spinnerPermittedBy False")
           return false
       }
       if(TextUtils.isEmpty(binding.transhipmentRemarks.text.toString().trim()))
       {
           Log.e("transhipmentRemarks","transhipmentRemarks False")
           return false
       }


       return true
   }



    fun DriverIsDone():Boolean
    {
        if(TextUtils.isEmpty(binding?.drLicenceNo?.text.toString()))
        {
            Log.e("docketNo","docketNo False")
            return false
        }
        if(TextUtils.isEmpty(binding?.drMobileNo?.text.toString()))
        {
            Log.e("docketDate","docketDate False")
            return false
        }
        if(TextUtils.isEmpty(binding?.drAlternateMobileNo?.text.toString()))
        {
            Log.e("address","address False")
            return false
        }
        if(TextUtils.isEmpty(binding?.drDriverName?.text.toString()))
        {
            Log.e("gst","gst False")
            return false
        }
        if(TextUtils.isEmpty(binding?.drDateOfBirth?.text.toString()))
        {
            Log.e("fromCity","fromCity False")
            return false
        }
        val text_three: String = binding.spinerDrLicenseType?.getSelectedItem().toString()
        if(TextUtils.isEmpty(text_three.toString()))
        {
            Log.e("fromState","fromState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.drLicenseValidityDt?.text.toString()))
        {
            Log.e("toCity","toCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.drIssuedByRTO?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }




        return true
    }

    fun BrokerIsDone():Boolean
    {
        if(TextUtils.isEmpty(binding?.brMobileNo?.text.toString()))
        {
            Log.e("docketNo","docketNo False")
            return false
        }
        if(TextUtils.isEmpty(binding?.brFirmName?.text.toString()))
        {
            Log.e("docketDate","docketDate False")
            return false
        }
        if(TextUtils.isEmpty(binding?.brContactPersonName?.text.toString()))
        {
            Log.e("address","address False")
            return false
        }
        if(TextUtils.isEmpty(binding?.brAadhaarNo?.text.toString()))
        {
            Log.e("gst","gst False")
            return false
        }
        if(TextUtils.isEmpty(binding?.brPANNo?.text.toString()))
        {
            Log.e("fromCity","fromCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.brCity?.text.toString()))
        {
            Log.e("fromState","fromState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.toCity?.text.toString()))
        {
            Log.e("toCity","toCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.brBrokerRegNo?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }

        if(TextUtils.isEmpty(binding?.brChooseFile?.text.toString()))
        {
            Log.e("spinnerLoadType","spinnerLoadType False")
            return false
        }



        return true
    }

fun billFormIsDone():Boolean
{

    if(TextUtils.isEmpty(binding.consignor?.text.toString()))
    {
        Log.e("consignor","consignor False")
        return false
    }
    if(TextUtils.isEmpty(binding.consigneeType?.text.toString().trim()))
    {
        Log.e("consignee_type","consignee_type False")
        return false
    }
    if(TextUtils.isEmpty(binding.otherType?.text.toString().trim()))
    {
        Log.e("origin","origin False")
        return false
    }

    return true
}

    @SuppressLint("NewApi")
    private fun setTextViewDrawableColor(textView: TextView, @ColorRes color: Int) {
        textView.setTextColor(getColor(color));
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter =
                    PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN)
            }
        }
    }

    override fun invoiceNext() {

        // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())


        AdditionalDetails(true,true)
    }

    override fun aditionNext() {
        binding.thcTitle?.setCompoundDrawablesWithIntrinsicBounds(
            getResources().getDrawable(R.drawable.five_icon),
            null,
            getResources().getDrawable(R.drawable.down_arrow_icon),
            null
        )
        binding.thcTitle?.setTextColor(getColor(R.color.primery_text));
        binding.thcLayout?.visibility = View.VISIBLE
    }
    fun brokerNext(v:View)
    {

        var vId:Int=v.id
        if(vId==R.id.nextBroker) {

            if ( BrokerIsDone()) {
                binding.brokerTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.brokerTitle?.setTextColor(getColor(R.color.blue));
                binding.brokerLayout.visibility = View.GONE

                // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
//                 setInvoiceData(true,true)

                binding.drTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.six_icon),
                    null, getResources().getDrawable(R.drawable.down_arrow_icon),
                    null)
                binding.drTitle?.setTextColor(getColor(R.color.primery_text));
                binding.drLayout.visibility = View.VISIBLE
            }
            else{
                Toast.makeText(this,"Please fill to Broker Info Form",Toast.LENGTH_LONG).show()

            }


        }
        else{


            if(docketIsDone()) {
                if(binding.brokerLayout?.isVisible==true) {

                    binding.brokerTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.next_form_icon_blue),
                        null
                    )
                    binding.brokerLayout?.visibility = View.GONE
                }
                else{

                    binding.brokerTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                        null
                    )
                    binding.brokerLayout?.visibility = View.VISIBLE
                    //  binding.billLayout?.visibility = View.GONE
                }


            }



        }


    }



    fun driverNext(v:View)
    {

        var vId:Int=v.id
        if(vId==R.id.driverNext) {

            if ( DriverIsDone()) {
                binding.drTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.drTitle?.setTextColor(getColor(R.color.blue));
                binding.drLayout.visibility = View.GONE

                // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
//                 setInvoiceData(true,true)

                binding.frTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.eight_icon),
                    null, getResources().getDrawable(R.drawable.down_arrow_icon),
                    null)
                binding.frTitle?.setTextColor(getColor(R.color.primery_text));
                binding.frLayout.visibility = View.VISIBLE
            }
            else{
                Toast.makeText(this,"Please fill to Driver Info Form",Toast.LENGTH_LONG).show()

            }


        }
        else{


            if((DriverIsDone())) {
                if(binding.drLayout?.isVisible==true) {

                    binding.drTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.next_form_icon_blue),
                        null
                    )
                    binding.drLayout?.visibility = View.GONE


                }
                else{

                    binding.drTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                        null
                    )
                    binding.drLayout?.visibility = View.VISIBLE
                    //  binding.billLayout?.visibility = View.GONE
                }


            }



        }


    }



    fun FreightNext(v:View)
    {

        var vId:Int=v.id
        if(vId==R.id.nextFr) {

            if ( FreightIsDone()) {
                binding.frTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.frTitle?.setTextColor(getColor(R.color.blue));
                binding.frLayout.visibility = View.GONE

                // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
//                 setInvoiceData(true,true)

                binding.adTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.nine_icon),
                    null, getResources().getDrawable(R.drawable.down_arrow_icon),
                    null)
                binding.adTitle?.setTextColor(getColor(R.color.primery_text));
                binding.adLayout.visibility = View.VISIBLE
            }
            else{
                Toast.makeText(this,"Please fill to Freight/Loading Info Form",Toast.LENGTH_LONG).show()

            }


        }
        else{


            if((DriverIsDone())) {
                if(binding.frLayout?.isVisible==true) {

                    binding.frTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.next_form_icon_blue),
                        null
                    )
                    binding.frLayout?.visibility = View.GONE


                }
                else{

                    binding.frTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                        null
                    )
                    binding.frLayout?.visibility = View.VISIBLE
                    //  binding.billLayout?.visibility = View.GONE
                }


            }



        }


    }



    fun adwanceNext(v:View)
    {

        var vId:Int=v.id
        if(vId==R.id.submit) {

            if ( FreightIsDone()) {
                binding.adTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.done_mark_icon),
                    null, getResources().getDrawable(R.drawable.form_but_next_blue),
                    null)
                binding.adTitle?.setTextColor(getColor(R.color.blue));
                binding.adLayout.visibility = View.GONE

                // TransitionManager.beginDelayedTransition(invoice_layout!!, AutoTransition())
//                 setInvoiceData(true,true)

//                binding.adTitle?.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.nine_icon),
//                    null, getResources().getDrawable(R.drawable.down_arrow_icon),
//                    null)
//                binding.frTitle?.setTextColor(getColor(R.color.primery_text));
//                binding.frLayout.visibility = View.VISIBLE
            }
            else{

                Toast.makeText(this,"Please fill to Advance Details Info Form",Toast.LENGTH_LONG).show()
            }


        }
        else{


            if((DriverIsDone())) {
                if(binding.adLayout?.isVisible==true) {

                    binding.adTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.next_form_icon_blue),
                        null
                    )
                    binding.adLayout?.visibility = View.GONE


                }
                else{

                    binding.adTitle?.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.done_mark_icon),
                        null,
                        getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                        null
                    )
                    binding.adLayout?.visibility = View.VISIBLE
                    //  binding.billLayout?.visibility = View.GONE
                }


            }



        }


    }

    fun FreightIsDone():Boolean
    {
        val text_three: String = binding.spinerFrLoadType?.getSelectedItem().toString()
        if(TextUtils.isEmpty(text_three.toString()))
        {
            Log.e("docketNo","docketNo False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frDocketActualWt?.text.toString()))
        {
            Log.e("docketDate","docketDate False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frTHCWt?.text.toString()))
        {
            Log.e("address","address False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frRate?.text.toString()))
        {
            Log.e("gst","gst False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frFreightAmount?.text.toString()))
        {
            Log.e("fromCity","fromCity False")
            return false
        }

        if(TextUtils.isEmpty(binding.frHandlingCharges.toString()))
        {
            Log.e("fromState","fromState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frDeadWt?.text.toString()))
        {
            Log.e("toCity","toCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frMamul?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frMamulCharged?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frOthers?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.frDetentionLoading?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }




        return true
    }



    fun AdvanceIsDone():Boolean
    {

        if(TextUtils.isEmpty(binding?.adNetContractAmt?.text.toString()))
        {
            Log.e("docketDate","docketDate False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adTdsOne?.text.toString()))
        {
            Log.e("address","address False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adTdsTwo?.text.toString()))
        {
            Log.e("gst","gst False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adAdvanceAmtOne?.text.toString()))
        {
            Log.e("fromCity","fromCity False")
            return false
        }

        if(TextUtils.isEmpty(binding.adAdvanceAmtTwo.toString()))
        {
            Log.e("fromState","fromState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adBalanceAmt?.text.toString()))
        {
            Log.e("toCity","toCity False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adAdvanceCashOne?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adAdvanceCashTwo?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adAdvanceFuelOne?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adAdvanceFuelTwo?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }


        if(TextUtils.isEmpty(binding?.adAdvanceBankOne?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adAdvanceBankTwo?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adRemainingAdvance?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        if(TextUtils.isEmpty(binding?.adClaimAmount?.text.toString()))
        {
            Log.e("toState","toState False")
            return false
        }
        val text_three: String = binding.spinerBalancePayableAt?.getSelectedItem().toString()
        if(TextUtils.isEmpty(text_three.toString()))
        {
            Log.e("docketNo","docketNo False")
            return false
        }


        return true
    }






    fun barScane(v:View)
    {

        startForResult.launch(Intent(this, BarScanner::class.java))
        val intent = Intent(this, BarScanner::class.java)
        startActivityForResult(intent, 4400)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 4400) {
            if (resultCode == Activity.RESULT_OK) {

                // Get String data from Intent
                val returnString = data!!.getStringExtra("barcode")

                binding.docketNo.setText(returnString.toString())
            }
        }
    }
fun onClickVehiclDetails(v:View)
{
   var bottomSheetFragment = VehicalDetails()
    bottomSheetFragment.show(supportFragmentManager, "BSDialogFragment")
}

    fun onClickPodDetails(v:View)
    {
        var bottomSheetFragment = PodDetails()
        bottomSheetFragment.show(supportFragmentManager, "BSDialogFragment")
    }
}

