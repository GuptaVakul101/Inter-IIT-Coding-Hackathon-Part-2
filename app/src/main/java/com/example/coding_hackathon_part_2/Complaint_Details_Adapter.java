//package com.example.coding_hackathon_part_2;
//
////public class Complaint_Details_Adapter {
////}
//
//import android.content.ClipData;
//import android.content.Context;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class Complaint_Details_Adapter extends RecyclerView.Adapter<Complaint_Details_Adapter.ViewHolder>
//{
//    Context context;
//    List<Item> mainUploadlist;
//
//    public ShowItemsAdapter(Context context, List<Item> Templist)
//    {
//        this.mainUploadlist = Templist;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
//    {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_layout, viewGroup, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
//    {
//        ClipData.Item itemdetails = mainUploadlist.get(position);
//
//        holder.txtProductName.setText("Name = " + itemdetails.getName());
//        holder.txtProductCategory.setText("Category = " + itemdetails.getCategory());
//        holder.txtProductPrice.setText("Price = " + itemdetails.getPrice());
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return mainUploadlist.size();
//    }
//
//
//    class ViewHolder extends RecyclerView.ViewHolder
//    {
//        public TextView txtProductName, txtProductPrice, txtProductCategory;
//
//        public ViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//
//            txtProductName = itemView.findViewById(R.id.product_name);
//            txtProductPrice = itemView.findViewById(R.id.product_price);
//            txtProductCategory = itemView.findViewById(R.id.product_category);
//        }
//
//    }
//}