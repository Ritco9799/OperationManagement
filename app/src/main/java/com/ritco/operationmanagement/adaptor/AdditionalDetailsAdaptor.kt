package com.ritco.operationmanagement.adaptor

import android.content.Context
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
import com.ritco.operationmanagement.modules.AFormData
import com.ritco.operationmanagement.modules.AdditionalDetailsFormList
import com.ritco.operationmanagement.modules.FormData
import com.ritco.operationmanagement.modules.InvoiceFormList

class AdditionalDetailsAdaptor (val context: Context, val formList:ArrayList<AdditionalDetailsFormList>, var nextForm: InvoiceForm) : RecyclerView.Adapter<AdditionalDetailsAdaptor.ViewHolder>()
{

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val invoiceDetailsTitle: TextView = itemView.findViewById<TextView>(R.id.aditionDetailsTitle)
        val invoiceFormAdd: TextView = itemView.findViewById<TextView>(R.id.additionFormAdd)
        val invoiceDetailsNect: TextView = itemView.findViewById<TextView>(R.id.next)
        val formLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.aditionFormLayout)
        var remark: EditText =itemView.findViewById(R.id.remark)
        var AdditionalCharges: EditText =itemView.findViewById(R.id.AdditionalCharges)
        var AddChargRemarks: EditText =itemView.findViewById(R.id.AddChargRemarks)
        val removeForm: TextView = itemView.findViewById<TextView>(R.id.removeForm)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalDetailsAdaptor.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.additional_details_form, parent, false)

        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: AdditionalDetailsAdaptor.ViewHolder, position: Int) {
        var fs:Int=formList.size-1
        if(fs==position)
        {
            viewHolder.invoiceFormAdd?.visibility= View.VISIBLE
        }
        else{
            if(formList.get(position).formComplite) {
                viewHolder.invoiceFormAdd?.visibility = View.GONE
            }
        }
        if (position==0)
        {
            viewHolder.removeForm.visibility= View.GONE
        }
        else{
            viewHolder.removeForm.visibility= View.VISIBLE
        }

        if(formList.get(position).showForm==true)
        {
            viewHolder.formLayout?.visibility= View.VISIBLE
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
                    context.getResources().getDrawable(R.drawable.four_icon),
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
                    context.getResources().getDrawable(R.drawable.four_icon),
                    null,
                    context. getResources().getDrawable(R.drawable.next_form_icon),
                    null
                )
            }
        }







        viewHolder.removeForm.setOnClickListener {

            formList.removeAt(position)
            notifyDataSetChanged()

        }

        viewHolder.invoiceDetailsNect?.setOnClickListener {



            if(TextUtils.isEmpty(viewHolder.remark.text))
            {
                viewHolder.remark.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.AdditionalCharges.text))
            {
                viewHolder.AdditionalCharges.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.AddChargRemarks.text))
            {
                viewHolder.AddChargRemarks.setError("")
            }


            else {


                nextAdition(position,viewHolder)
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
                    var formData: AFormData = AFormData(
                        "",
                        ",",
                        ","
                    )
                    var data: AdditionalDetailsFormList = AdditionalDetailsFormList(
                        iss,
                        formList.get(position).formComplite,
                        true, formData
                    )
                    formList.set(position, data)
                    notifyDataSetChanged()
                } else {
                    for (i in 0..formList.size - 1) {


                        if (i == position) {
                            Log.e("setData", "" + i)
                            if (formList.get(i).showForm == true) {
                                Log.e("setData", "is visible")
                                var formData: AFormData = AFormData(
                                    "",
                                    ",",
                                    ",",
                                )
                                var data: AdditionalDetailsFormList = AdditionalDetailsFormList(
                                    false,
                                    formList.get(i).formComplite,
                                    true, formData
                                )
                                formList.set(i, data)
                            } else {
                                Log.e("setData", "no visible")
                                var formData: AFormData = AFormData(
                                    "",
                                    ",",
                                    ","
                                )
                                var data: AdditionalDetailsFormList =
                                    AdditionalDetailsFormList(
                                        true,
                                        formList.get(i).formComplite,
                                        true, formData
                                    )
                                formList.set(i, data)
                            }


                        } else {
                            var formData: AFormData =
                                AFormData(
                                    "",
                                    ",",
                                    ",",
                                )
                            var data: AdditionalDetailsFormList =
                                AdditionalDetailsFormList(
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




            if(TextUtils.isEmpty(viewHolder.remark.text))
            {
                viewHolder.remark.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.AdditionalCharges.text))
            {
                viewHolder.AdditionalCharges.setError("")
            }
            else if(TextUtils.isEmpty(viewHolder.AddChargRemarks.text))
            {
                viewHolder.AddChargRemarks.setError("")
            }
            else {
                viewHolder.formLayout?.visibility = View.GONE
                addInvoice(position)
            }

        }

    }


    fun addInvoice(position: Int)
    {
        var formData1: AFormData = AFormData("",",","")
        var data1: AdditionalDetailsFormList = AdditionalDetailsFormList(false,true,true,formData1)
        var formData: AFormData = AFormData("",",","")
        var data: AdditionalDetailsFormList = AdditionalDetailsFormList(true,false,true,formData)
        formList.set(position,data1)
        formList.add(data)
        notifyDataSetChanged()

    }
    fun nextAdition(po: Int,viewHolder: AdditionalDetailsAdaptor.ViewHolder)
    {

        var formData: AFormData = AFormData("",",",",")
        var data: AdditionalDetailsFormList = AdditionalDetailsFormList(false,true,true,formData)
        formList.set(po,data)
        notifyDataSetChanged()
        nextForm.aditionNext()

    }


    override fun getItemCount(): Int {

        return formList.size
    }
}