<template>
    <div class="container">
        <div class="w3-container w3-light-grey">
            <h2 class="personal-title">퍼스널컬러 진단 결과</h2>
        </div>
        <div style="display:flex;">
            <div class="personal-image">
                <img :src="befimg" v-if="befimg" alt="기본 이미지">
            </div>
            <div style="margin-top:0px;">
                <div class="res-btncon row" >
                    <div @click="pageChange(1)" class="res-button-start col-1" style="margin-left: 50px;">Q1</div>
                    <div @click="pageChange(2)" class="res-button-end col-1">Q2</div>
                </div>                        
                <div :style="displayPage(1)" class="personal-con row">
                    <div class="personal-highcharts"  style="margin-top:0px">
                        <div id="highchart"></div>            
                    </div>                    
                </div>
                <div :style="displayPage(2)" class="personal-con row">
                    <div class="personal-highcharts" style="height:450px;">
                        <div style=" margin: 103.75px 0 103.75px 0;">
                            <p><span style="color: lightblue;">하늘색</span> 넥타이가 잘어울릴듯</p><br>
                            <p><span style="color: pink;">핑크색</span> 와이셔츠가 잘어울릴듯</p><br>
                            <p><span style="color: navy;">남색</span> 블레이져가 잘어울릴듯</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="personal-btn-grp">
          <button class="btn btn-next personal-btn" @click="movepage" style="width: 240px;">가상 MakeUp</button>
        </div>
    </div>
</template>

<script>
import { useRoute } from 'vue-router';
import axios from "axios";
export default {
    data() {
        return {
            befimg: localStorage.getItem('befimg'),
            befimgn: localStorage.getItem('befimgn'),
            activePage: 1
        }
    },
    setup(){
        // 하이차트에 들어갈 값
        // useRoute 훅을 사용하여 현재 라우트 객체를 가져옵니다.
    const route = useRoute();
return {
    season : route.query.season,
    spring : parseFloat(route.query.spring),
    summer : parseFloat(route.query.summer),
    autumn : parseFloat(route.query.autumn),
    winter : parseFloat(route.query.winter),
};
    },
    methods: {
        pageChange(pageNum) {
            this.activePage = pageNum;
            if (pageNum === 1) {
                this.$nextTick(() => {
                    this.highchart();
                });
            }
        },
        dataURItoBlob(dataURI) {
            const byteString = atob(dataURI.split(',')[1]);
            const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
            const ab = new ArrayBuffer(byteString.length);
            const ia = new Uint8Array(ab);
            for (let i = 0; i < byteString.length; i++) {
                ia[i] = byteString.charCodeAt(i);
            }
            return new Blob([ab], { type: mimeString });
        },


        displayPage(pageNum) {
            return this.activePage === pageNum ? { display: 'block' } : { display: 'none' };
        },
        async cuttingImage(){
            const cuttingImageformData = new FormData();
            cuttingImageformData.append('imgfile', this.dataURItoBlob(this.befimg), this.befimgn);
            for (let [key, value] of cuttingImageformData.entries()) {
                console.log(key, ':', value);
                }
            const response =await axios.post(`${process.env.VUE_APP_DJANGO_APP_BACK_END_URL}personalcol/cuttingImage`, cuttingImageformData, {
                headers: { 'Content-Type': 'multipart/form-data' },
                responseType: 'blob'  // blob으로 응답받기 위해 설정
            });
            

            const reader = new FileReader();
            reader.onloadend = function() {
                const base64data = reader.result;
                localStorage.setItem('uploadedImage', base64data);
                
                // 이미지 로드 및 표시
            };
                
            await reader.readAsDataURL(response.data);
                
            

            

            },




        highchart() {
            let bestTone="Total"
            let toneTotal=0
            if((this.spring+this.autumn)>(this.summer+this.winter)){
                bestTone= "웜톤";
                toneTotal = this.spring+this.autumn
            }else{
                bestTone= "쿨톤";
                toneTotal = this.summer+this.winter
            }
            console.log(bestTone)
            console.log(toneTotal)
            Highcharts.chart('highchart', {
                chart: {
                    type: 'pie',
                    backgroundColor: 'rgba(218, 218, 218, 0)',
                    custom: {},
                    events: {
                        render() {
                            const chart = this,
                                series = chart.series[0];
                            let customLabel = chart.options.chart.custom.label;

                            if (!customLabel) {
                                customLabel = chart.options.chart.custom.label =
                                    chart.renderer.label(
                                        bestTone+' Total'+'<br/>' +
                                        '<strong>'+toneTotal+'%</strong><br/>'
                                    )
                                    .css({
                                        color: '#000',
                                        textAnchor: 'middle'
                                    })
                                    .add();
                            }

                            const x = series.center[0] + chart.plotLeft,
                                y = series.center[1] + chart.plotTop -
                                (customLabel.attr('height') / 2);

                            customLabel.attr({
                                x,
                                y
                            });
                            customLabel.css({
                                fontSize: `${series.center[2] / 12}px`
                            });
                        }
                    }
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                title: {
                    text: this.season+' '+bestTone
                },
                subtitle: {
                    text: '퍼스널컬러 비율'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.0f}%</b>'
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    series: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        borderRadius: 8,
                        dataLabels: [{
                            enabled: true,
                            distance: 20,
                            format: '{point.name}'
                        }, {
                            enabled: true,
                            distance: -15,
                            format: '{point.percentage:.0f}%',
                            style: {
                                fontSize: '0.9em'
                            }
                        }],
                        showInLegend: true
                    }
                },
                series: [{
                    name: 'Registrations',
                    colorByPoint: true,
                    innerSize: '75%',
                    data: [{
                        name: '봄 웜톤',
                        y: this.spring
                    }, {
                        name: '여름 쿨톤',
                        y: this.summer
                    }, {
                        name: '가을 웜톤',
                        y: this.autumn
                    }, {
                        name: '겨울 쿨톤',
                        y: this.winter
                    }]
                }]
            });
        },
        movepage() {
            
            //this.$router.push('/personal_MakeUp');
            console.log(this.season);
            this.$router.push({name:"PersonalCol_MakeUp",query:{season:this.season}});
        }
    },
    mounted() {
        this.cuttingImage();
        if (this.activePage === 1) {
            this.highchart();
        }
        
    }
}
</script>