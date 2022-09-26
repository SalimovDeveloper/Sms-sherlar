package uz.programmer710.smssherlar.fragments

import android.Manifest
import android.content.*
import android.content.Context.CLIPBOARD_SERVICE
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.programmer710.smssherlar.R
import uz.programmer710.smssherlar.adapters.MvAdapter
import uz.programmer710.smssherlar.adapters.RvAdapter
import uz.programmer710.smssherlar.databinding.DialogViewBinding
import uz.programmer710.smssherlar.databinding.FragmentSherlarBinding
import uz.programmer710.smssherlar.models.Info
import uz.programmer710.smssherlar.models.MyObject
import uz.programmer710.smssherlar.models.MySharedPreference


class Sherlar : Fragment() {
    private lateinit var binding: FragmentSherlarBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var mvAdapter: MvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSherlarBinding.inflate(layoutInflater)
        val msg = arguments?.getString("keyName")

        MySharedPreference.init(requireContext())


        if (msg == "Yangi Sherlar"){
            binding.infoToolbar.text = "Yangi sherlar"
            rvAdapter = RvAdapter(MyObject.list_Yangi_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            val list = MySharedPreference.list
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter
        }else if (msg == "Sevgi Sherlari") {
            binding.infoToolbar.text = "Sevgi Sherlari"
            rvAdapter = RvAdapter(MyObject.list_Sevgi_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter

        }else if (msg == "Sog'inch Sherlari") {
            binding.infoToolbar.text = "Sog'inch Sherlari"
            rvAdapter = RvAdapter(MyObject.list_Soginch_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter
        } else if (msg == "Tabrik Sherlari") {
            binding.infoToolbar.text = "Tabrik Sherlari"
            rvAdapter = RvAdapter(MyObject.list_Tabrik_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter
        } else if (msg == "Ota Ona Sherlari") {
            binding.infoToolbar.text = "Ota Ona Sherlari"
            rvAdapter = RvAdapter(MyObject.list_Ota_Ona_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter
        } else if (msg == "Do'stlik Sherlari") {
            binding.infoToolbar.text = "Do'stlik Sherlari"
            rvAdapter = RvAdapter(MyObject.list_Dostlik_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter
        }else if (msg == "Hazil Sherlari") {
            binding.infoToolbar.text = "Hazil Sherlari"
            rvAdapter = RvAdapter(MyObject.list_Hazil_Sherlar, object : RvAdapter.RvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)
                        }else{
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.myRv.adapter = rvAdapter
        }else if (msg == "Saqlanganlar"){
            binding.infoToolbar.text = "Saqlangan Sherlar"
            mvAdapter = MvAdapter(MyObject.list_Saqlangan_Sherlar, object : MvAdapter.MvClick{
                override fun itemClick(info: Info) {
                    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
                    val dialogViewBinding = DialogViewBinding.inflate(layoutInflater)
                    dialogViewBinding.sheetsSherNomi.text = info.name
                    dialogViewBinding.sheetsSherMatni.text = info.sher
                    dialogViewBinding.btnYurak.setImageResource(R.drawable.qizil_yurak)
                    bottomSheetDialog.setContentView(dialogViewBinding.root)
                    bottomSheetDialog.show()
                    dialogViewBinding.sendMessage.setOnClickListener {
                        sendSmsText()
                        val msg = info.sher
                        if (msg.isNotEmpty()){
                            val smsIntent = Intent(Intent.ACTION_VIEW)
                            smsIntent.data = Uri.parse("sms:" + "$msg")
                            startActivity(smsIntent)
                        }
                    }
                    dialogViewBinding.btnYurak.setOnClickListener {
                        if (MyObject.list_Saqlangan_Sherlar.contains(info)){
                            Toast.makeText(
                                requireContext(),
                                "Saqlanganllar ro'yhatidan o'chirildi",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialogViewBinding.btnYurak.setImageResource(R.drawable.yurak_icon)
                            MyObject.list_Saqlangan_Sherlar.remove(info)

                        }else{
                            MyObject.list_Saqlangan_Sherlar.add(info)
                            Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialogViewBinding.btnShare.setOnClickListener {
                        val message = info.sher.toString()
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Yuborish"))
                    }
                    dialogViewBinding.btnCopy.setOnClickListener {
                        val clipboard =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("TextView", info.sher)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            binding.myRv.adapter = mvAdapter
        }

        return binding.root
    }

    private fun sendSmsText(){
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.RECEIVE_SMS), 101)
        }
    }
}