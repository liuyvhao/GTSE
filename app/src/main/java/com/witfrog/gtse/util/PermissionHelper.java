package com.witfrog.gtse.util;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;

import java.util.List;

public class PermissionHelper {

    public static void requestCalendar(final OnPermissionGrantedListener grantedListener,
                                       final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.CALENDAR);
    }

    public static void requestCamera(final OnPermissionGrantedListener grantedListener,
                                     final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.CAMERA);
    }

    public static void requestContacts(final OnPermissionGrantedListener grantedListener,
                                       final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.CONTACTS);
    }

    public static void requestLocation(final OnPermissionGrantedListener grantedListener,
                                       final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.LOCATION);
    }

    public static void requestMicrophone(final OnPermissionGrantedListener grantedListener,
                                         final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.MICROPHONE);
    }

    public static void requestPhone(final OnPermissionGrantedListener grantedListener,
                                    final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.PHONE);
    }

    public static void requestSensors(final OnPermissionGrantedListener grantedListener,
                                      final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.SENSORS);
    }

    public static void requestSMS(final OnPermissionGrantedListener grantedListener,
                                  final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.SMS);
    }

    public static void requestStorage(final OnPermissionGrantedListener grantedListener,
                                      final OnPermissionDeniedListener deniedListener) {
        request(grantedListener, deniedListener, PermissionConstants.STORAGE);
    }

    public static void request(final OnPermissionGrantedListener grantedListener,
                               final OnPermissionDeniedListener deniedListener,
                               final @PermissionConstants.Permission String... permissions) {
        PermissionUtils.permission(permissions)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        if (grantedListener != null) {
                            grantedListener.onPermissionGranted();
                        }
                        LogUtils.d(permissionsGranted);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog();
                        }
                        if (deniedListener != null) {
                            deniedListener.onPermissionDenied();
                        }
                        LogUtils.d(permissionsDeniedForever, permissionsDenied);
                    }
                })
                .request();
    }

    public interface OnPermissionGrantedListener {
        void onPermissionGranted();
    }

    public interface OnPermissionDeniedListener {
        void onPermissionDenied();
    }

}
