/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.teach.type.ClassType;
import com.teach.type.CoursewareType;
import com.teach.type.PlanType;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class RequestQueue {
    private Queue<String> queue = new ConcurrentLinkedQueue<String>();
    private boolean isComplete = false;
    private ClassType classType;
    private PlanType planType;
    private CoursewareType coursewareType;

    public synchronized String getRequest() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    public synchronized void addRequest(String url) {
        queue.add(url);
        notifyAll();
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public CoursewareType getCoursewareType() {
        return coursewareType;
    }

    public void setCoursewareType(CoursewareType coursewareType) {
        this.coursewareType = coursewareType;
    }
}
