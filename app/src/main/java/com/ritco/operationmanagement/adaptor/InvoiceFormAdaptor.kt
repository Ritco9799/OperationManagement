package com.ritco.operationmanagement.adaptor

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ritco.om.R
import com.ritco.operationmanagement.interfaces.InvoiceForm
import com.ritco.operationmanagement.modules.FormData
import com.ritco.operationmanagement.modules.InvoiceFormList

class InvoiceFormAdaptor (val context: Context, val formList:ArrayList<InvoiceFormList>,var nextForm: InvoiceForm) : RecyclerView.Adapter<InvoiceFormAdaptor.ViewHolder>()
{

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val invoiceDetailsTitle: TextView = itemView.findViewById<TextView>(R.id.invoiceDetailsTitle)
        val invoiceFormAdd: TextView = itemView.findViewById<TextView>(R.id.invoiceFormAdd)
        val invoiceDetailsNect: TextView = itemView.findViewById<TextView>(R.id.invoiceDetailsNect)
        val formLayout:LinearLayout= itemView.findViewById<LinearLayout>(R.id.formLayout)
        var InvoiceNo:EditText=itemView.findViewById(R.id.InvoiceNo)
        var E_wayBillNo:EditText=itemView.findViewById(R.id.E_wayBillNo)
        var DONo:EditText=itemView.findViewById(R.id.DONo)
        var CustomerRefNo:EditText=itemView.findViewById(R.id.CustomerRefNo)
        var NoOfPackages:EditText=itemView.findViewById(R.id.NoOfPackages)
        var DeclaredValue:EditText=itemView.findViewById(R.id.DeclaredValue)
        var ActualWt:EditText=itemView.findViewById(R.id.ActualWt)
        var ChargeWt:EditText=itemView.findViewById(R.id.ChargeWt)
        var TotalActualWt:EditText=itemView.findViewById(R.id.TotalActualWt)
        var TotalChargedWt:EditText=itemView.findViewById(R.id.TotalChargedWt)
        val InvoiceDt:TextView= itemView.findViewById<TextView>(R.id.InvoiceDt)
         val removeForm:TextView= itemView.findViewById<TextView>(R.id.removeForm)
        var spiner_SaidToContain:Spinner=itemView.findViewById(R.id.spiner_SaidToContain)
        var spiner_TypesOfPackages:Spinner=itemView.findViewById(R.id.spiner_TypesOfPackages)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceFormAdaptor.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.invoice_form, parent, false)

        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: InvoiceFormAdaptor.ViewHolder, position: Int) {
        var fs:Int=formList.size-1
        if(fs==position)
        {
            viewHolder.invoiceFormAdd?.visibility=View.VISIBLE
        }
        else{
            if(formList.get(position).formComplite) {
                viewHolder.invoiceFormAdd?.visibility = View.GONE
            }
        }
     if (position==0)
     {
         viewHolder.removeForm.visibility=View.GONE
     }
        else{
         viewHolder.removeForm.visibility=View.VISIBLE
     }

        if(formList.get(position).showForm==true)
        {
            viewHolder.formLayout?.visibility=View.VISIBLE
            if(formList.get(position).formComplite==true)
            {
                viewHolder.invoiceDetailsTitle.setTextColor(context.getColor(R.color.blue));
               viewHolder.invoiceDetailsTitle.setCompoundDrawablesWithIntrinsicBounds(
                   context.getResources().getDrawable(R.drawable.done_mark_icon),
                    null,
                   context. getResources().getDrawable(R.drawable.down_arrow_icon_blue),
                    null
                )
            }
            else{
                viewHolder.invoiceDetailsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(R.drawable.three_icon),
                    null,
                    context. getResources().getDrawable(R.drawable.down_arrow_icon),
                    null
                )
            }
        }
        else {
            viewHolder.formLayout?.visibility = View.GONE
            if(formList.get(position).formComplite==true)
            {
                Log.e("com form","true "+position)
                viewHolder.invoiceDetailsTitle.setTextColor(context.getColor(R.color.blue));
                viewHolder.invoiceDetailsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(R.drawable.done_mark_icon),
                    null,
                    context. getResources().getDrawable(R.drawable.next_form_icon_blue),
                    null
                )
            }
            else{
                Log.e("com form","false "+position)
                viewHolder.invoiceDetailsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(R.drawable.three_icon),
                    null,
                    context. getResources().getDrawable(R.drawable.next_form_icon),
                    null
                )
            }
        }



        setSpiner(viewHolder.spiner_SaidToContain)
        setSpiner(viewHolder.spiner_TypesOfPackages)



        viewHolder.removeForm.setOnClickListener {

            formList.removeAt(position)
            notifyDataSetChanged()

        }

         viewHolder.invoiceDetailsNect?.setOnClickListener {

             val text: String = viewHolder.spiner_SaidToContain?.getSelectedItem().toString()
             val text1: String = viewHolder.spiner_TypesOfPackages?.getSelectedItem().toString()
             if(TextUtils.isEmpty(viewHolder.ActualWt.text))
             {
                 viewHolder.ActualWt.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.InvoiceNo.text))
             {
                 viewHolder.InvoiceNo.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.E_wayBillNo.text))
             {
                 viewHolder.E_wayBillNo.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.DONo.text))
             {
                 viewHolder.DONo.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.DeclaredValue.text))
             {
                 viewHolder.DeclaredValue.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.CustomerRefNo.text))
             {
                 viewHolder.CustomerRefNo.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.NoOfPackages.text))
             {
                 viewHolder.NoOfPackages.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.ChargeWt.text))
             {
                 viewHolder.ChargeWt.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.TotalActualWt.text))
             {
                 viewHolder.TotalActualWt.setError("")
             }

             else if(TextUtils.isEmpty(viewHolder.TotalChargedWt.text))
             {
                 viewHolder.TotalChargedWt.setError("")
             }
             else if(TextUtils.isEmpty(viewHolder.InvoiceDt.text))
             {
                 viewHolder.InvoiceDt.setError("")
             }

            else if(TextUtils.isEmpty(text.trim()))
             {


             }
             else if(TextUtils.isEmpty(text1.trim()))
             {


             }
             else {


                 viewHolder.formLayout?.visibility = View.GONE
                 nextInvoice(position)
             }

         }
        viewHolder.invoiceDetailsTitle?.setOnClickListener {
            if (formList.get(position).openForm==true) {
                var iss: Boolean = true
                if (viewHolder.formLayout?.isVisible == true) {
                    viewHolder.formLayout?.visibility = View.GONE
                    iss = false
                } else {
                    viewHolder.formLayout?.visibility = View.VISIBLE
                    iss = true
                }

                if (formList.size == 1) {
                    Log.e("Show form", "true")
                    var formData: FormData = FormData(
                        "",
                        ",",
                        ",",
                        ",",
                        ",",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        0,
                        0,
                        0,
                        0
                    )
                    var data: InvoiceFormList = InvoiceFormList(
                        iss,
                        formList.get(position).formComplite,
                        true,formData
                    )
                    formList.set(position, data)
                    notifyDataSetChanged()
                } else {
                    for (i in 0..formList.size - 1) {


                        if (i == position) {
                            Log.e("setData", "" + i)
                            if (formList.get(i).showForm == true) {
                                Log.e("setData", "is visible")
                                var formData: FormData = FormData(
                                    "",
                                    ",",
                                    ",",
                                    ",",
                                    ",",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    0,
                                    0,
                                    0,
                                    0
                                )
                                var data: InvoiceFormList = InvoiceFormList(
                                    false,
                                    formList.get(i).formComplite,
                                    true, formData
                                )
                                formList.set(i, data)
                            } else {
                                Log.e("setData", "no visible")
                                var formData: FormData = FormData(
                                    "",
                                    ",",
                                    ",",
                                    ",",
                                    ",",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    0,
                                    0,
                                    0,
                                    0
                                )
                                var data: InvoiceFormList =
                                    InvoiceFormList(
                                        true,
                                        formList.get(i).formComplite,
                                        true,formData
                                    )
                                formList.set(i, data)
                            }


                        } else {
                            var formData: FormData =
                                FormData(
                                    "",
                                    ",",
                                    ",",
                                    ",",
                                    ",",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    0,
                                    0,
                                    0,
                                    0
                                )
                            var data: InvoiceFormList =
                                InvoiceFormList(
                                    false,
                                    formList.get(i).formComplite,
                                    true, formData
                                )
                            formList.set(i, data)
                        }

                    }
                    notifyDataSetChanged()

                }
            }

        }

        viewHolder.invoiceFormAdd?.setOnClickListener {


            val text: String = viewHolder.spiner_SaidToContain?.getSelectedItem().toString()
            val text1: String = viewHolder.spiner_TypesOfPackages?.getSelectedItem().toString()
            if(TextUtils.isEmpty(viewHolder.ActualWt.text))
            {
                viewHolder.ActualWt.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.InvoiceNo.text))
            {
                viewHolder.InvoiceNo.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.E_wayBillNo.text))
            {
                viewHolder.E_wayBillNo.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.DONo.text))
            {
                viewHolder.DONo.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.DeclaredValue.text))
            {
                viewHolder.DeclaredValue.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.CustomerRefNo.text))
            {
                viewHolder.CustomerRefNo.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.NoOfPackages.text))
            {
                viewHolder.NoOfPackages.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.ChargeWt.text))
            {
                viewHolder.ChargeWt.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.TotalActualWt.text))
            {
                viewHolder.TotalActualWt.setError("")
            }

            else if(TextUtils.isEmpty(viewHolder.TotalChargedWt.text))
            {
                viewHolder.TotalChargedWt.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.InvoiceDt.text))
            {
                viewHolder.InvoiceDt.setError("")
            }

            else if(TextUtils.isEmpty(text.trim()))
            {


            }
            else if(TextUtils.isEmpty(text1.trim()))
            {


            }
            else {
                viewHolder.formLayout?.visibility = View.GONE
                addInvoice(position)
            }

        }

    }


    fun addInvoice(position: Int)
    {
        var formData1: FormData = FormData("",",",",",",",",","","","","","","",0,0,0,0)
        var data1:InvoiceFormList=InvoiceFormList(false,true,true,formData1)
        var formData: FormData = FormData("",",",",",",",",","","","","","","",0,0,0,0)
        var data:InvoiceFormList=InvoiceFormList(true,false,true,formData)
        formList.set(position,data1)
        formList.add(data)
        notifyDataSetChanged()

    }
    fun nextInvoice(po: Int)
    {



        var formData: FormData = FormData("",",",",",",",",","","","","","","",0,0,0,0)
        var data:InvoiceFormList=InvoiceFormList(false,true,true,formData)
        formList.set(po,data)
        notifyDataSetChanged()
        nextForm.invoiceNext()




    }
    fun setSpiner(spinner: Spinner)
    {
        val years = arrayOf("items1", "it2ems", "items3", "items5")
        val langAdapter = ArrayAdapter<CharSequence>(context, R.layout.spinner_text, years)
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown)
        spinner?.setAdapter(langAdapter)

    }

    override fun getItemCount(): Int {

        return formList.size
    }
}