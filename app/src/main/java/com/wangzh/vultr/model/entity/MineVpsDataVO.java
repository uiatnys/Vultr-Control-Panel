package com.wangzh.vultr.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99210 on 2017/5/13.
 */

public class MineVpsDataVO implements Parcelable{

    public static final String  VALUE_SUBID = "SUBID";
    public static final String  VALUE_OS = "os";
    public static final String  VALUE_RAM = "ram";
    public static final String  VALUE_DISK = "disk";
    public static final String  VALUE_MAINIP = "main_ip";
    public static final String  VALUE_VCPUCOUNT = "vcpu_count";
    public static final String  VALUE_LOCATION = "location";
    public static final String  VALUE_DCID = "DCID";
    public static final String  VALUE_DEFAULTPASSWORD = "default_password";
    public static final String  VALUE_DATECREATED = "date_created";
    public static final String  VALUE_PENDINGCHARGES = "pending_charges";
    public static final String  VALUE_STATUS = "status";
    public static final String  VALUE_COSTPERMONTH = "cost_per_month";
    public static final String  VALUE_CURRENTBANDWITHGB = "current_bandwidth_gb";
    public static final String  VALUE_ALLOWEDBANDWIDTHGB = "allowed_bandwidth_gb";
    public static final String  VALUE_NETMASKV4 = "netmask_v4";
    public static final String  VALUE_GATEWAYV4 = "gateway_v4";
    public static final String  VALUE_POWERSTATUS = "power_status";
    public static final String  VALUE_SERVERSTATE = "server_state";
    public static final String  VALUE_VPSPLANID = "VPSPLANID";
    public static final String  VALUE_MAINIPV6 = "v6_main_ip";
    public static final String  VALUE_NETWORKSIZEV6 = "v6_network_size";
    public static final String  VALUE_NETWORKV6 = "v6_network";
    public static final String  VALUE_LABEL = "label";
    public static final String  VALUE_INTERNALIP = "internal_ip";
    public static final String  VALUE_KVMURL = "kvm_url";
    public static final String  VALUE_AUTOBACKUPS = "auto_backups";
    public static final String  VALUE_TAG = "tag";
    public static final String  VALUE_OSID = "OSID";
    public static final String  VALUE_APPID = "APPID";
    public static final String  VALUE_FIREWALLGROUP = "FIREWALLGROUPID";

    public static final String  VALUE_NETWORKS_V6 = "v6_networks";
    public static final String  VALUE_MAINIP_V6 = "v6_main_ip";
    public static final String  VALUE_NETWORKSIZE_V6 = "v6_network_size";
    public static final String  VALUE_NETWORK_V6 = "v6_network";

        /**
         * SUBID : 7739993
         * os : CentOS 6 x64
         * ram : 512 MB
         * disk : Virtual 20 GB
         * main_ip : 45.77.5.250
         * vcpu_count : 1
         * location : Silicon Valley
         * DCID : 12
         * default_password : 7K@gyc7U6nf5sxV]
         * date_created : 2017-04-01 06:10:46
         * pending_charges : 1.14
         * status : active
         * cost_per_month : 2.50
         * current_bandwidth_gb : 5.458
         * allowed_bandwidth_gb : 500
         * netmask_v4 : 255.255.254.0
         * gateway_v4 : 45.77.4.1
         * power_status : running
         * server_state : ok
         * VPSPLANID : 200
         * v6_main_ip : 2001:19f0:ac01:19b:5400:00ff:fe61:3e78
         * v6_network_size : 64
         * v6_network : 2001:19f0:ac01:19b::
         * v6_networks : [{"v6_main_ip":"2001:19f0:ac01:19b:5400:00ff:fe61:3e78","v6_network_size":"64","v6_network":"2001:19f0:ac01:19b::"}]
         * label : Vultr_SiliconVally
         * internal_ip :
         * kvm_url : https://my.vultr.com/subs/vps/novnc/api.php?data=M5UXGVBPIJTTCSCTG5WFUWRQJQ4GUWC2FNWDSK2GGFWVOOLFNZCHKZRTI5YFCUSSLBCVA3KDKZHU2ZD2F43EE53YK5CU42LHKZHWMNRWKRRW2N2NM5DWK33LLJUFQMBYPF2FCZBTJQVU4RTIOVMHERZROBZUIN2DLJGDKTCYJFWGO6JWII3FSTRXMNQUEMC2J5MUW5BLIZ3EW
         * auto_backups : no
         * tag :
         * OSID : 127
         * APPID : 0
         * FIREWALLGROUPID : 0
         */

        private String SUBID;
        private String os;
        private String ram;
        private String disk;
        private String main_ip;
        private String vcpu_count;
        private String location;
        private String DCID;
        private String default_password;
        private String date_created;
        private String pending_charges;
        private String status;
        private String cost_per_month;
        private String current_bandwidth_gb;
        private String allowed_bandwidth_gb;
        private String netmask_v4;
        private String gateway_v4;
        private String power_status;
        private String server_state;
        private String VPSPLANID;
        private String v6_main_ip;
        private String v6_network_size;
        private String v6_network;
        private String label;
        private String internal_ip;
        private String kvm_url;
        private String auto_backups;
        private String tag;
        private String OSID;
        private String APPID;
        private String FIREWALLGROUPID;
        private List<V6NetworksBean> v6_networks;

        public String getSUBID() {
            return SUBID;
        }

        public void setSUBID(String SUBID) {
            this.SUBID = SUBID;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getRam() {
            return ram;
        }

        public void setRam(String ram) {
            this.ram = ram;
        }

        public String getDisk() {
            return disk;
        }

        public void setDisk(String disk) {
            this.disk = disk;
        }

        public String getMain_ip() {
            return main_ip;
        }

        public void setMain_ip(String main_ip) {
            this.main_ip = main_ip;
        }

        public String getVcpu_count() {
            return vcpu_count;
        }

        public void setVcpu_count(String vcpu_count) {
            this.vcpu_count = vcpu_count;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDCID() {
            return DCID;
        }

        public void setDCID(String DCID) {
            this.DCID = DCID;
        }

        public String getDefault_password() {
            return default_password;
        }

        public void setDefault_password(String default_password) {
            this.default_password = default_password;
        }

        public String getDate_created() {
            return date_created;
        }

        public void setDate_created(String date_created) {
            this.date_created = date_created;
        }

        public String getPending_charges() {
            return pending_charges;
        }

        public void setPending_charges(String pending_charges) {
            this.pending_charges = pending_charges;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCost_per_month() {
            return cost_per_month;
        }

        public void setCost_per_month(String cost_per_month) {
            this.cost_per_month = cost_per_month;
        }

        public String getCurrent_bandwidth_gb() {
            return current_bandwidth_gb;
        }

        public void setCurrent_bandwidth_gb(String current_bandwidth_gb) {
            this.current_bandwidth_gb = current_bandwidth_gb;
        }

        public String getAllowed_bandwidth_gb() {
            return allowed_bandwidth_gb;
        }

        public void setAllowed_bandwidth_gb(String allowed_bandwidth_gb) {
            this.allowed_bandwidth_gb = allowed_bandwidth_gb;
        }

        public String getNetmask_v4() {
            return netmask_v4;
        }

        public void setNetmask_v4(String netmask_v4) {
            this.netmask_v4 = netmask_v4;
        }

        public String getGateway_v4() {
            return gateway_v4;
        }

        public void setGateway_v4(String gateway_v4) {
            this.gateway_v4 = gateway_v4;
        }

        public String getPower_status() {
            return power_status;
        }

        public void setPower_status(String power_status) {
            this.power_status = power_status;
        }

        public String getServer_state() {
            return server_state;
        }

        public void setServer_state(String server_state) {
            this.server_state = server_state;
        }

        public String getVPSPLANID() {
            return VPSPLANID;
        }

        public void setVPSPLANID(String VPSPLANID) {
            this.VPSPLANID = VPSPLANID;
        }

        public String getV6_main_ip() {
            return v6_main_ip;
        }

        public void setV6_main_ip(String v6_main_ip) {
            this.v6_main_ip = v6_main_ip;
        }

        public String getV6_network_size() {
            return v6_network_size;
        }

        public void setV6_network_size(String v6_network_size) {
            this.v6_network_size = v6_network_size;
        }

        public String getV6_network() {
            return v6_network;
        }

        public void setV6_network(String v6_network) {
            this.v6_network = v6_network;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getInternal_ip() {
            return internal_ip;
        }

        public void setInternal_ip(String internal_ip) {
            this.internal_ip = internal_ip;
        }

        public String getKvm_url() {
            return kvm_url;
        }

        public void setKvm_url(String kvm_url) {
            this.kvm_url = kvm_url;
        }

        public String getAuto_backups() {
            return auto_backups;
        }

        public void setAuto_backups(String auto_backups) {
            this.auto_backups = auto_backups;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getOSID() {
            return OSID;
        }

        public void setOSID(String OSID) {
            this.OSID = OSID;
        }

        public String getAPPID() {
            return APPID;
        }

        public void setAPPID(String APPID) {
            this.APPID = APPID;
        }

        public String getFIREWALLGROUPID() {
            return FIREWALLGROUPID;
        }

        public void setFIREWALLGROUPID(String FIREWALLGROUPID) {
            this.FIREWALLGROUPID = FIREWALLGROUPID;
        }

        public List<V6NetworksBean> getV6_networks() {
            return v6_networks;
        }

        public void setV6_networks(List<V6NetworksBean> v6_networks) {
            this.v6_networks = v6_networks;
        }

    public static class V6NetworksBean {
            /**
             * v6_main_ip : 2001:19f0:ac01:19b:5400:00ff:fe61:3e78
             * v6_network_size : 64
             * v6_network : 2001:19f0:ac01:19b::
             */

            private String v6_main_ip;
            private String v6_network_size;
            private String v6_network;

            public String getV6_main_ip() {
                return v6_main_ip;
            }

            public void setV6_main_ip(String v6_main_ip) {
                this.v6_main_ip = v6_main_ip;
            }

            public String getV6_network_size() {
                return v6_network_size;
            }

            public void setV6_network_size(String v6_network_size) {
                this.v6_network_size = v6_network_size;
            }

            public String getV6_network() {
                return v6_network;
            }

            public void setV6_network(String v6_network) {
                this.v6_network = v6_network;
            }
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.SUBID);
        dest.writeString(this.os);
        dest.writeString(this.ram);
        dest.writeString(this.disk);
        dest.writeString(this.main_ip);
        dest.writeString(this.vcpu_count);
        dest.writeString(this.location);
        dest.writeString(this.DCID);
        dest.writeString(this.default_password);
        dest.writeString(this.date_created);
        dest.writeString(this.pending_charges);
        dest.writeString(this.status);
        dest.writeString(this.cost_per_month);
        dest.writeString(this.current_bandwidth_gb);
        dest.writeString(this.allowed_bandwidth_gb);
        dest.writeString(this.netmask_v4);
        dest.writeString(this.gateway_v4);
        dest.writeString(this.power_status);
        dest.writeString(this.server_state);
        dest.writeString(this.VPSPLANID);
        dest.writeString(this.v6_main_ip);
        dest.writeString(this.v6_network_size);
        dest.writeString(this.v6_network);
        dest.writeString(this.label);
        dest.writeString(this.internal_ip);
        dest.writeString(this.kvm_url);
        dest.writeString(this.auto_backups);
        dest.writeString(this.tag);
        dest.writeString(this.OSID);
        dest.writeString(this.APPID);
        dest.writeString(this.FIREWALLGROUPID);
        dest.writeList(this.v6_networks);
    }

    public MineVpsDataVO() {
    }

    protected MineVpsDataVO(Parcel in) {
        this.SUBID = in.readString();
        this.os = in.readString();
        this.ram = in.readString();
        this.disk = in.readString();
        this.main_ip = in.readString();
        this.vcpu_count = in.readString();
        this.location = in.readString();
        this.DCID = in.readString();
        this.default_password = in.readString();
        this.date_created = in.readString();
        this.pending_charges = in.readString();
        this.status = in.readString();
        this.cost_per_month = in.readString();
        this.current_bandwidth_gb = in.readString();
        this.allowed_bandwidth_gb = in.readString();
        this.netmask_v4 = in.readString();
        this.gateway_v4 = in.readString();
        this.power_status = in.readString();
        this.server_state = in.readString();
        this.VPSPLANID = in.readString();
        this.v6_main_ip = in.readString();
        this.v6_network_size = in.readString();
        this.v6_network = in.readString();
        this.label = in.readString();
        this.internal_ip = in.readString();
        this.kvm_url = in.readString();
        this.auto_backups = in.readString();
        this.tag = in.readString();
        this.OSID = in.readString();
        this.APPID = in.readString();
        this.FIREWALLGROUPID = in.readString();
        this.v6_networks = new ArrayList<V6NetworksBean>();
        in.readList(this.v6_networks, V6NetworksBean.class.getClassLoader());
    }

    public static final Creator<MineVpsDataVO> CREATOR = new Creator<MineVpsDataVO>() {
        @Override
        public MineVpsDataVO createFromParcel(Parcel source) {
            return new MineVpsDataVO(source);
        }

        @Override
        public MineVpsDataVO[] newArray(int size) {
            return new MineVpsDataVO[size];
        }
    };
}
