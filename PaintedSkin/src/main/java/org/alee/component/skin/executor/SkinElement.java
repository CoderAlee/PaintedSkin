package org.alee.component.skin.executor;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;

import org.alee.component.skin.pack.ResourcesType;

import java.util.Objects;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public final class SkinElement {
    /**
     * 属性名 例如 textColor
     */
    private String mAttrName;
    /**
     * 资源Id
     */
    private int mResourcesId;
    /**
     * 资源类型
     */
    private String mResourcesType;
    /**
     * 资源名称
     */
    private String mResourcesName;

    public SkinElement(@NonNull String attrName, @AnyRes int resourcesId) {
        mAttrName = attrName;
        mResourcesId = resourcesId;
    }

    public SkinElement(@NonNull String attrName, @AnyRes int resourcesId, @ResourcesType.Constraint String resourcesType, @NonNull String resourcesName) {
        mAttrName = attrName;
        mResourcesId = resourcesId;
        mResourcesType = resourcesType;
        mResourcesName = resourcesName;
    }

    public String getAttrName() {
        return null == mAttrName ? "" : mAttrName;
    }

    public int getResourcesId() {
        return mResourcesId;
    }


    public String getResourcesType() {
        return null == mResourcesType ? "" : mResourcesType;
    }

    public void setResourcesType(@ResourcesType.Constraint String resourcesType) {
        mResourcesType = resourcesType;
    }

    public String getResourcesName() {
        return null == mResourcesName ? "" : mResourcesName;
    }

    public void setResourcesName(@NonNull String resourcesName) {
        mResourcesName = resourcesName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAttrName, mResourcesId, mResourcesType, mResourcesName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SkinElement that = (SkinElement) o;
        return mResourcesId == that.mResourcesId &&
                Objects.equals(mAttrName, that.mAttrName) &&
                Objects.equals(mResourcesType, that.mResourcesType) &&
                Objects.equals(mResourcesName, that.mResourcesName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SkinElement{");
        sb.append("mAttrName='").append(mAttrName).append('\'');
        sb.append(", mResourcesId=").append(mResourcesId);
        sb.append(", mResourcesType='").append(mResourcesType).append('\'');
        sb.append(", mResourcesName='").append(mResourcesName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
